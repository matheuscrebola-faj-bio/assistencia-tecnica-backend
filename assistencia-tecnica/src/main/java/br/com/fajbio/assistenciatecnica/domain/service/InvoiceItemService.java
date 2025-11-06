package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.InvoiceItemReq;
import br.com.fajbio.assistenciatecnica.domain.model.InvoiceItem;
import br.com.fajbio.assistenciatecnica.domain.repository.InvoiceItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceItemService {
    private final InvoiceItemRepository repository;

    public InvoiceItem encontrarPeloId(Long itemId) {
        return repository.findById(itemId).orElse(null);
    }

    @Transactional
    public InvoiceItem cadastrar(InvoiceItem item) {
        return repository.save(item);
    }

    @Transactional
    public void atualizar(Long itemId, InvoiceItemReq req) {
        var invoice = encontrarPeloId(itemId);
        invoice.setDescricao(req.descricao());
        invoice.setQuantidade(req.quantidade());
        invoice.setPrecoUnitario(req.precoUnitario());
    }

    @Transactional
    public void deletar(Long itemId) {
        repository.deleteById(itemId);
    }
}
