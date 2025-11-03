package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.SoDocument;
import br.com.fajbio.assistenciatecnica.domain.repository.SoDocumentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoDocumentService {
    private final SoDocumentRepository repository;

    @Transactional
    protected SoDocument salvar(SoDocument document){
        return repository.save(document);
    }

    public SoDocument cadastrar(SoDocument document) {
        return salvar(document);
    }

    public SoDocument encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
