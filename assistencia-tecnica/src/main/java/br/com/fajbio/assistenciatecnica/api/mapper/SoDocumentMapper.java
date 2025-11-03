package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.SoDocumentRes;
import br.com.fajbio.assistenciatecnica.domain.enums.ETipoDoc;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.SoDocument;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class SoDocumentMapper {
    @Value( "${app.documentos.saidaDir}")
    private static String ARMAZENAMENTO_LOCAL;

    public SoDocument mappear(ServiceOrder service) {
        return SoDocument.builder()
                .serviceOrderId(service.getId())
                .serviceOrder(service)
                .tipoDoc(ETipoDoc.FORMULARIO_DE_SERVICO)
                .nome("Formulário de Serviço"+"-"
                        +service.getId()+"-"
                        + LocalDateTime.now().format(
                                DateTimeFormatter
                                        .ofPattern("dd-MM-yyyy HH:mm:ss")))
                .filePath(ARMAZENAMENTO_LOCAL)
                .criadoEm(LocalDateTime.now())
                .build();
    }

    public SoDocument mappear(SoDocument document, Path path) {
        return SoDocument.builder()
                .serviceOrderId(document.getServiceOrderId())
                .serviceOrder(document.getServiceOrder())
                .tipoDoc(ETipoDoc.FORMULARIO_DE_SERVICO)
                .nome("Formulário de Serviço"+"-"
                        +document.getId()+"-"
                        + LocalDateTime.now().format(
                        DateTimeFormatter
                                .ofPattern("dd-MM-yyyy HH:mm:ss")))
                .filePath(path.toString())
                .criadoEm(LocalDateTime.now())
                .build();
    }

    public SoDocumentRes mappear(SoDocument soDocument) {
        return SoDocumentRes.builder()
                .id(soDocument.getId())
                .tipoDoc(soDocument.getTipoDoc())
                .nome(soDocument.getNome())
                .filePath(soDocument.getFilePath())
                .criadoEm(soDocument.getCriadoEm())
                .build();
    }
}
