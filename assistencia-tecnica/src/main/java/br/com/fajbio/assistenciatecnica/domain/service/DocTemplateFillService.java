package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrderReq;
import lombok.RequiredArgsConstructor;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Serviço para preencher templates DOCX com placeholders ${chave}.
 * Possui métodos específicos por tipo de documento e um método genérico
 * reaproveitável para qualquer Map<String,String> + template.
 */
@Service
@RequiredArgsConstructor
public class DocTemplateFillService {
    private final ResourceLoader resourceLoader;

    @Value("${docs.output-dir}")
    private String outputDir;

    // Templates configurados no application.yml
    @Value("${docs.templates.service-order}")
    private String serviceOrderTemplatePath;
    private static final DateTimeFormatter BRAZIL_DATE = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    public Path preencherServiceOrder(ServiceOrderReq req) throws Exception {
        Map<String, String> vars = mapVarsFrom(req);

        String fileBase = "ordem-servico-" + UUID.randomUUID();
        Path saida = Paths.get(outputDir, fileBase + ".docx");

        preencherTemplate(serviceOrderTemplatePath, vars, saida);
        return saida;
    }

    /**
     * Método genérico: carrega o template DOCX (classpath:, file:, http: se suportado),
     * substitui placeholders e salva em 'destinoDocx'.
     */
    public void preencherTemplate(String templateLocation,
                                  Map<String, String> vars,
                                  Path destinoDocx) throws Exception {

        Resource res = resourceLoader.getResource(templateLocation);
        if (!res.exists()) {
            throw new IllegalStateException("Template não encontrado em: " + templateLocation);
        }

        // Garante diretório de saída
        Files.createDirectories(destinoDocx.getParent());

        try (InputStream is = res.getInputStream()) {
            WordprocessingMLPackage pkg = WordprocessingMLPackage.load(is);
            MainDocumentPart mdp = pkg.getMainDocumentPart();

            // Substitui ${chave} por valor
            mdp.variableReplace(vars);

            // Salva DOCX preenchido
            try (OutputStream os = new FileOutputStream(destinoDocx.toFile())) {
                pkg.save(os);
            }
        }
    }

    /**
     * Monta o mapa de variáveis esperado no template DOCX
     * a partir do record ServiceOrderReq.
     *
     * No DOCX, use placeholders como:
     * ${empresa}, ${cnpj}, ${contato}, ${setor}, ${email}, ${cep},
     * ${endereco}, ${produto}, ${serial}, ${ultimaCalibracao}, ${descricao}
     */
    private Map<String, String> mapVarsFrom(ServiceOrderReq req) {
        Map<String, String> m = new HashMap<>();

        // Strings simples (evita null no DOCX)
        m.put("empresa", nvl(req.empresa()));
        m.put("cnpj", nvl(req.cnpj()));
        m.put("contato", nvl(req.contato()));
        m.put("setor", nvl(req.setor()));
        m.put("email", nvl(req.email()));
        m.put("cep", nvl(req.cep()));
        m.put("endereco", nvl(req.endereco()));

        // Produto (enum) → texto
        String produtoStr = req.produto() != null ? req.produto().name() : "";
        // Se quiser um nome “bonito”, crie um método getDisplayName() no enum.
        m.put("produto", produtoStr);

        m.put("serial", nvl(req.serial()));

        // Data formatada
        m.put("ultimaCalibracao", format(req.ultimaCalibracao()));

        // Texto livre
        m.put("descricao", nvl(req.descricao()));

        // Você pode adicionar variáveis “derivadas”, ex. data de hoje:
        m.putIfAbsent("dataAtual", BRAZIL_DATE.format(LocalDate.now()));

        return m;
    }

    private String nvl(String s) {
        return s == null ? "" : s;
    }

    private String format(LocalDate d) {
        return d == null ? "" : BRAZIL_DATE.format(d);
    }
}
