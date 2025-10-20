package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrderReq;
import lombok.RequiredArgsConstructor;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final DateTimeFormatter BR_DATE = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    /**
     * Preenche a Ordem de Serviço com os placeholders MAIÚSCULOS do seu DOCX.
     * @param req        dados base
     * @param telefone   opcional (preenche ${TELEFONE})
     * @param modelo     opcional; se vazio, usa label do enum produto
     * @return           caminho do DOCX preenchido
     */
    public Path preencherServiceOrder(ServiceOrderReq req,
                                      String telefone,
                                      String modelo) throws Exception {
        Map<String, String> vars = montarMapaPlaceholders(req, telefone, modelo);

        // arquivo de saída
        Files.createDirectories(Paths.get(outputDir));
        String filename = "ordem-servico-" + UUID.randomUUID() + ".docx";
        Path destino = Paths.get(outputDir, filename);

        preencherTemplate(serviceOrderTemplatePath, vars, destino);
        return destino;
    }

    /** Genérico: carrega template, substitui placeholders e salva. */
    public void preencherTemplate(String templateLocation,
                                  Map<String, String> vars,
                                  Path destinoDocx) throws Exception {
        Resource res = resourceLoader.getResource(templateLocation);
        if (!res.exists()) {
            throw new IllegalStateException("Template não encontrado em: " + templateLocation);
        }

        try (InputStream is = res.getInputStream()) {
            WordprocessingMLPackage pkg = WordprocessingMLPackage.load(is);
            MainDocumentPart mdp = pkg.getMainDocumentPart();

            // (Opcional) avisar placeholders que não foram preenchidos
            Set<String> faltantes = placeholdersNoTemplate(mdp);
            faltantes.removeAll(vars.keySet());
            if (!faltantes.isEmpty()) {
                // Aqui apenas loga
                System.out.println("Placeholders sem valor: " + faltantes);
            }

            mdp.variableReplace(vars);

            try (OutputStream os = new FileOutputStream(destinoDocx.toFile())) {
                pkg.save(os);
            }
        }
    }

    /** Mapeia o record + extras para as CHAVES do seu DOCX. */
    private Map<String, String> montarMapaPlaceholders(ServiceOrderReq req,
                                                       String telefone,
                                                       String modelo) {
        Map<String, String> m = new HashMap<>();

        // Datas
        m.put("DATA_ABERTURA", BR_DATE.format(LocalDate.now())); // aparece 2x no seu template

        // Básicos
        m.put("PROFISSIONAL_SAUDE", nvl(req.contato()));
        m.put("SETOR", nvl(req.setor()));
        m.put("EMPRESA", nvl(req.empresa()));
        m.put("CNPJ", nvl(req.cnpj()));
        m.put("EMAIL", nvl(req.email()));
        m.put("TELEFONE", nvl(telefone));

        // Endereço completo + CEP
        String enderecoCompleto = nvl(req.endereco());
        if (req.cep() != null && !req.cep().isBlank()) {
            enderecoCompleto = enderecoCompleto.isBlank()
                    ? "CEP " + req.cep()
                    : enderecoCompleto + " - CEP " + req.cep();
        }
        m.put("ENDERECO", enderecoCompleto);

        // Modelo do equipamento
        String modeloFinal = nvl(modelo);
        if (modeloFinal.isBlank()) {
            // tenta usar o rótulo do enum; se não tiver, usa name()
            if (req.produto() == null) {
                modeloFinal = "";
            } else {
                modeloFinal = req.produto().name();
            }
        }
        m.put("MODELO", modeloFinal);

        // Lote / Nº de Série
        m.put("LOTE", nvl(req.serial()));

        // Última calibração (vou cobrir 2 chaves possíveis)
        String dataUltCal = req.ultimaCalibracao() == null ? "" : BR_DATE.format(req.ultimaCalibracao());
        m.put("DATA_ULTIMA_CALIBRACAO", dataUltCal);
        m.put("ULTIMA_CALIBRACAO", dataUltCal);

        // Motivo do retorno
        m.put("MOTIVO", nvl(req.descricao()));

        return m;
    }

    private String nvl(String s) { return s == null ? "" : s; }

    // --- utilitário opcional: detectar ${CHAVES} no DOCX para avisar faltantes ---
    private static final Pattern VAR = Pattern.compile("\\$\\{([^}]+)}");

    private Set<String> placeholdersNoTemplate(MainDocumentPart mdp) {
        String xml = mdp.getXML();
        Matcher matcher = VAR.matcher(xml);
        Set<String> keys = new HashSet<>();
        while (matcher.find()) {
            keys.add(matcher.group(1));
        }
        return keys;
    }

    /** Interface opcional para enums com rótulo amigável. */
    public interface HasLabel {
        String getLabel();
    }
}
