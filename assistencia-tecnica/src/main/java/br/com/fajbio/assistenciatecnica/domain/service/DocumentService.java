package br.com.fajbio.assistenciatecnica.domain.service;

import lombok.RequiredArgsConstructor;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.Docx4J;

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
     * @param docxPath mapa ex.: "empresa"->"ACME", "cnpj"->"00.000.000/0001-00"
     * @return Path do PDF salvo
     */
    public Path gerarPdfFromDocx(Path docxPath) throws Exception {
        if (docxPath == null || !Files.exists(docxPath)) {
            throw new IllegalArgumentException("DOCX inexistente: " + docxPath);
        }

        var pkg = WordprocessingMLPackage.load(docxPath.toFile());
        var fo = Docx4J.createFOSettings();
        fo.setWmlPackage(pkg);

        Files.createDirectories(Paths.get(outputDir));
        String outName = docxPath.getFileName().toString().replaceAll("\\.docx$", "") + ".pdf";
        Path pdfPath = Paths.get(outputDir, outName);

        try (OutputStream os = new FileOutputStream(pdfPath.toFile())) {
            Docx4J.toFO(fo, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
        }
        return pdfPath;
    }
}
