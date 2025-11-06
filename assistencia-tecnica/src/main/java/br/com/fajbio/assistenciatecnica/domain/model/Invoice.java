package br.com.fajbio.assistenciatecnica.domain.model;

import br.com.fajbio.assistenciatecnica.domain.enums.EInvoice;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_invoices", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_invoice_so", columnList = "service_order_id"),
        @Index(name = "idx_invoice_numero", columnList = "numero", unique = true),
        @Index(name = "idx_invoice_data", columnList = "data_emissao"),
        @Index(name = "idx_invoice_status", columnList = "invoice_status")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_order_id", nullable = false)
    private Long serviceOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", insertable = false, updatable = false)
    private ServiceOrder serviceOrder;

    @Column(name = "numero", unique = true, length = 100)
    private String numero;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "impostos", precision = 10, scale = 2)
    private BigDecimal impostos;

    @Column(name = "invoice_status", length = 50)
    @Enumerated(EnumType.STRING)
    private EInvoice status;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> items;
}