package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.domain.enums.ETipoDoc;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.SoDocument;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
}
