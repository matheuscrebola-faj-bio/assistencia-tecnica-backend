package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_quote_items", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_quoteitem_quote", columnList = "quote_id"),
        @Index(name = "idx_quoteitem_service", columnList = "service_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quote_id", nullable = false)
    private Long quoteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", insertable = false, updatable = false)
    private Quote quote;

    @Column(name = "service_id")
    private Long serviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private Service service;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "quantidade", precision = 10, scale = 2)
    private Byte quantidade;

    @Column(name = "preco_unitario", precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "desconto_pct", precision = 5, scale = 2)
    private BigDecimal descontoPct;

    @Column(name = "imposto_pct", precision = 5, scale = 2)
    private BigDecimal impostoPct;
}