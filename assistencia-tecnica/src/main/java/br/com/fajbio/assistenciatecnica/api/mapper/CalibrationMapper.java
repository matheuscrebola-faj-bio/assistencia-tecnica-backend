package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.CalibrationReq;
import br.com.fajbio.assistenciatecnica.api.dto.CalibrationRes;
import br.com.fajbio.assistenciatecnica.domain.model.Calibration;
import br.com.fajbio.assistenciatecnica.domain.model.CalibrationPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CalibrationMapper {
    public CalibrationRes mapear(Calibration c) {
        return CalibrationRes.builder()
                .id(c.getId())
                .data(c.getData())
                .validade(c.getValidade())
                .build();
    }

    public Calibration mapear(CalibrationReq req, Calibration c) {
        // Usa valores atuais como padrão
        LocalDate data = c.getData();
        LocalDate validade = c.getValidade();
        String referencia = c.getReferenciaCertificado();

        // Atualiza apenas se o novo valor não for nulo e for diferente
        if (req.data() != null && !req.data().isEqual(c.getData())) {
            data = req.data();
        }

        if (req.referenciaCertificado() != null && !req.referenciaCertificado().isBlank()) {
            referencia = req.referenciaCertificado();
        }

        if (req.validade() != null && !req.validade().isEqual(c.getValidade())) {
            validade = req.validade();
        }

        // Cria um novo objeto com os valores atualizados
        return Calibration.builder()
                .id(c.getId())
                .serviceOrderId(c.getServiceOrderId())
                .serviceOrder(c.getServiceOrder())
                .tecnicoId(c.getTecnicoId())
                .tecnico(c.getTecnico())
                .data(data)
                .referenciaCertificado(referencia)
                .validade(validade)
                .build();
    }

    public Calibration mapear(Calibration c, CalibrationPoint p) {
        List<CalibrationPoint> points = c.getPoints();
        points.add(p);
        return Calibration.builder()
                .id(c.getId())
                .serviceOrderId(c.getServiceOrderId())
                .serviceOrder(c.getServiceOrder())
                .tecnicoId(c.getTecnicoId())
                .tecnico(c.getTecnico())
                .data(c.getData())
                .referenciaCertificado(c.getReferenciaCertificado())
                .validade(c.getValidade())
                .points(points)
                .build();
    }
}
