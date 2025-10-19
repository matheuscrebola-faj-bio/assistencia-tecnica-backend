package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.CalibrationPointReq;
import br.com.fajbio.assistenciatecnica.api.dto.CalibrationPointRes;
import br.com.fajbio.assistenciatecnica.domain.model.Calibration;
import br.com.fajbio.assistenciatecnica.domain.model.CalibrationPoint;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalibrationPointMapper {

    public List<CalibrationPointRes> mapear(List<CalibrationPoint> points) {
        if (points == null || points.isEmpty()) {
            return List.of(); // retorna lista imutável vazia
        }

        return points.stream()
                .map(this::mapear)
                .collect(Collectors.toList());
    }

    public CalibrationPointRes mapear(CalibrationPoint point){
        return CalibrationPointRes.builder()
                .id(point.getId())
                .grandeza(point.getGrandeza())
                .valorNominal(point.getValorNominal())
                .valorMedido(point.getValorMedido())
                .incerteza(point.getIncerteza())
                .build();
    }

    public CalibrationPoint mapear(CalibrationPointReq req, Calibration c) {
        return CalibrationPoint.builder()
                .calibrationId(c.getId())
                .calibration(c)
                .grandeza(req.grandeza())
                .valorNominal(req.valorNominal())
                .valorMedido(req.valorMedido())
                .incerteza(req.incerteza())
                .build();
    }
}
