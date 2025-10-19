package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_invoice_items", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_invoiceitem_invoice", columnList = "invoice_id"),
        @Index(name = "idx_invoiceitem_quoteitem", columnList = "quote_item_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_id", nullable = false)
    private Long invoiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", insertable = false, updatable = false)
    private Invoice invoice;

    @Column(name = "quote_item_id")
    private Long quoteItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_item_id", insertable = false, updatable = false)
    private QuoteItem quoteItem;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "quantidade", precision = 10, scale = 2)
    private BigDecimal quantidade;

    @Column(name = "preco_unitario", precision = 10, scale = 2)
    private BigDecimal precoUnitario;
}