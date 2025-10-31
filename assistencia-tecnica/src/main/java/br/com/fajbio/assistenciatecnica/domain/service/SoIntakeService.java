package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.SoIntakeReq;
import br.com.fajbio.assistenciatecnica.domain.model.SoIntake;
import br.com.fajbio.assistenciatecnica.domain.repository.SoIntakeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoIntakeService {
    private final SoIntakeRepository repository;

    @Transactional
    protected SoIntake salvar(SoIntake intake){
        return repository.save(intake);
    }

    public SoIntake cadastrar(SoIntake intake) {
        return salvar(intake);
    }

    public SoIntake encontrarPeloIdDaServiceOrder(Long serviceOrderId) {
        return repository.findByServiceOrderId(serviceOrderId);
    }

    @Transactional
    public void atualizarIntake(Long serviceOrderId, Long intakeId, SoIntakeReq req) {
        SoIntake intake = encontrarPeloIdDaServiceOrder(serviceOrderId);
        if (intake.getId().equals(intakeId)){
            intake.setLacreIntacto(req.lacreIntacto());
            intake.setObservacoes(req.observacoes());
        }
    }
}
