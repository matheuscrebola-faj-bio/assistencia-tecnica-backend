package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.Quote;
import br.com.fajbio.assistenciatecnica.domain.model.QuoteEvent;
import br.com.fajbio.assistenciatecnica.domain.model.QuoteItem;
import br.com.fajbio.assistenciatecnica.domain.repository.QuoteItemRepository;
import br.com.fajbio.assistenciatecnica.domain.repository.QuoteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final QuoteItemRepository quoteItemRepository;

    @Transactional
    protected Quote salvar(Quote quote){
        return quoteRepository.save(quote);
    }

    @Transactional
    protected List<QuoteItem> salvar(List<QuoteItem> quotes){
        return quoteItemRepository.saveAll(quotes);
    }

    public Quote cadastrar(Quote quote) {
        return salvar(quote);
    }

    public List<QuoteItem> cadastrar(List<QuoteItem> quotes) {
        return salvar(quotes);
    }

    @Transactional
    public void adicionarItems(List<QuoteItem> quoteItens, Quote quote) {
        quote.getItems().addAll(quoteItens);
    }

    public Quote encontrarPeloId(Long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    @Transactional
    public void atualizar(Long quoteId, QuoteItem item, QuoteEvent event) {
        var quote = encontrarPeloId(quoteId);
        quote.getItems().add(item);
        quote.getEvents().add(event);
    }

    @Transactional
    public void deletar(Long quoteId) {
        quoteRepository.deleteById(quoteId);
    }
}
