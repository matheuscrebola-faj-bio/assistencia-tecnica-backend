package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.QuoteItemReq;
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

    public QuoteItem encontrarPeloId(Long itemId) {
        return repository.findById(itemId).orElse(null);
    }

    @Transactional
    public void atualizar(Long itemId, QuoteItemReq req) {
        var quote = encontrarPeloId(itemId);
        quote.setDescricao(req.descricao());
    }

    @Transactional
    public void deletar(Long itemId) {
        repository.deleteById(itemId);
    }
}
