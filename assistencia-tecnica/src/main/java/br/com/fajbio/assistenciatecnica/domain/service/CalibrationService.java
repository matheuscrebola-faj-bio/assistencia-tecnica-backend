package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.CalibrationPointRes;
import br.com.fajbio.assistenciatecnica.api.dto.CalibrationReq;
import br.com.fajbio.assistenciatecnica.domain.model.Calibration;
import br.com.fajbio.assistenciatecnica.domain.repository.CalibrationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Transactional
    public void atualizar(CalibrationReq req, Calibration calibration) {
        // Usa valores atuais como padrão
        LocalDate data = calibration.getData();
        LocalDate validade = calibration.getValidade();
        String referencia = calibration.getReferenciaCertificado();

        // Atualiza apenas se o novo valor não for nulo e for diferente
        if (req.data() != null && !req.data().isEqual(calibration.getData())) {
            data = req.data();
        }

        if (req.referenciaCertificado() != null && !req.referenciaCertificado().isBlank()) {
            referencia = req.referenciaCertificado();
        }

        if (req.validade() != null && !req.validade().isEqual(calibration.getValidade())) {
            validade = req.validade();
        }

        calibration.setData(data);
        calibration.setReferenciaCertificado(referencia);
        calibration.setValidade(validade);
    }

    @Transactional
    public void deletarPeloId(Long id) {
        repository.deleteById(id);
    }
}
