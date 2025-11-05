package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.QuoteEvent;
import br.com.fajbio.assistenciatecnica.domain.repository.QuoteEventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteEventService {
    private final QuoteEventRepository repository;

    @Transactional
    public QuoteEvent cadastrar(QuoteEvent event) {
        return repository.save(event);
    }
}
