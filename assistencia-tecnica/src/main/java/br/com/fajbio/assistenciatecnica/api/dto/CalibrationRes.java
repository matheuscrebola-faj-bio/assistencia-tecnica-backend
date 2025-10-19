package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.model.CalibrationPoint;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public class CalibrationRes {
    private Long id;
    private ServiceOrder serviceOrder;
    private User tecnico;
    private LocalDate data;
    private String referenciaCertificado;
    private LocalDate validade;
    private List<CalibrationPoint> points;
}
