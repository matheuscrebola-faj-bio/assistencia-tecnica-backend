package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.enums.EShipmentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentRes {
    private Long id;
    private String transportadora;
    private String codigoRastreio;
    private LocalDate dataEnvio;
    private EShipmentMode mode;
}
