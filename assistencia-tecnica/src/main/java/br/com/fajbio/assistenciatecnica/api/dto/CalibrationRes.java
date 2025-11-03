package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.model.CalibrationPoint;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalibrationRes {
    private Long id;
    private ServiceOrder serviceOrder;
    private User tecnico;
    private LocalDate data;
    private String referenciaCertificado;
    private LocalDate validade;
    private List<CalibrationPoint> points;
}
