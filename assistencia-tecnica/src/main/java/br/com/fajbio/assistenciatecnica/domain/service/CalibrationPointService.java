package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.CalibrationPoint;
import br.com.fajbio.assistenciatecnica.domain.repository.CalibrationPointRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalibrationPointService {
    CalibrationPointRepository repository;

    @Transactional
    private CalibrationPoint salvar(CalibrationPoint cp){
        return repository.save(cp);
    }

    public CalibrationPoint criar(CalibrationPoint cp) {
        return salvar(cp);
    }
}
