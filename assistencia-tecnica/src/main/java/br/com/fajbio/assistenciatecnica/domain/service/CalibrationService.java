package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.CalibrationPointRes;
import br.com.fajbio.assistenciatecnica.domain.model.Calibration;
import br.com.fajbio.assistenciatecnica.domain.repository.CalibrationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalibrationService {
    private final CalibrationRepository repository;

    @Transactional
    protected Calibration salvar(Calibration c){
        return repository.save(c);
    }

    public Calibration encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Calibration criar(Calibration c) {
        return salvar(c);
    }

    public List<CalibrationPointRes> buscarPontosPorId(Long id) {
        return repository.buscarPontosDeCalibracaoPeloId(id);
    }

    public Calibration atualizar(Calibration c) {
        return salvar(c);
    }

    @Transactional
    public void deletarPeloId(Long id) {
        repository.deleteById(id);
    }
}
