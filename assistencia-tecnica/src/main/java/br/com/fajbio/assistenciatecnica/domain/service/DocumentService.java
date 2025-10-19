package br.com.fajbio.assistenciatecnica.domain.service;

import lombok.RequiredArgsConstructor;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.model.datastorage.migration.VariablePrepare.MigrationResult;
import org.docx4j.model.datastorage.VariableReplace;
import org.docx4j.Docx4J;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final ResourceLoader resourceLoader;

    @Value("${docs.template-path}")
    private String templatePath;

    @Value("${docs.output-dir}")
    private String outputDir;


    /**
     * Gera PDF preenchendo variáveis no DOCX template.
     * @param variables mapa ex.: "empresa"->"ACME", "cnpj"->"00.000.000/0001-00"
     * @return Path do PDF salvo
     */
    public Path gerarPdf(Map<String, String> variables) throws Exception {
        // Carrega template do classpath
        Resource res = resourceLoader.getResource(templatePath);
        if (!res.exists()) {
            throw new IllegalStateException("Template não encontrado em: " + templatePath);
        }

        try (InputStream is = res.getInputStream()) {
            WordprocessingMLPackage pkg = WordprocessingMLPackage.load(is);
            MainDocumentPart mdp = pkg.getMainDocumentPart();

            // Substitui ${chave} por valor
            mdp.variableReplace(variables);

            // Garante diretório de saída
            Files.createDirectories(Paths.get(outputDir));
            String filename = "documento-" + UUID.randomUUID() + ".pdf";
            Path pdfPath = Paths.get(outputDir, filename);

            // Converte para PDF (via XSL-FO)
            FOSettings fo = Docx4J.createFOSettings();
            fo.setWmlPackage(pkg);

            try (OutputStream os = new FileOutputStream(pdfPath.toFile())) {
                // FLAG_EXPORT_PREFER_XSL tende a gerar PDFs mais estáveis
                Docx4J.toFO(fo, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
            }

            return pdfPath;
        }
    }
}
