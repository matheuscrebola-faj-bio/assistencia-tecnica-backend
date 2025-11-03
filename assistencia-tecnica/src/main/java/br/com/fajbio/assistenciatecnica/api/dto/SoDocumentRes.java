package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.ETipoDoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoDocumentRes {
    private Long id;
    private ETipoDoc tipoDoc;
    private String nome;
    private String filePath;
    private LocalDateTime criadoEm;
}
