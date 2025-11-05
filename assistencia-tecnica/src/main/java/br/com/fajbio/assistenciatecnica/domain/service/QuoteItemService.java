package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.QuoteItem;
import br.com.fajbio.assistenciatecnica.domain.repository.QuoteItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteItemService {
    private final QuoteItemRepository repository;
    @Transactional
    public QuoteItem cadastrar(QuoteItem item) {
        return repository.save(item);
    }
}
