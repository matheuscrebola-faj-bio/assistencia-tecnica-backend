package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.Quote;
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
}
