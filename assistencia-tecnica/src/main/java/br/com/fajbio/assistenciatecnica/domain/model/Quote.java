package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_quotes", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_quote_so", columnList = "service_order_id"),
        @Index(name = "idx_quote_status", columnList = "quote_status"),
        @Index(name = "idx_quote_validade", columnList = "validade"),
        @Index(name = "idx_quote_created", columnList = "criado_em")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_order_id", nullable = false)
    private Long serviceOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", insertable = false, updatable = false)
    private ServiceOrder serviceOrder;

    @Column(name = "quote_status", length = 50)
    private String status;

    @Column(name = "validade")
    private LocalDate validade;

    @Column(name = "created_by_user_id")
    private Long createdByUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", insertable = false, updatable = false)
    private User createdBy;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuoteItem> items;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuoteEvent> events;
}