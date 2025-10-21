package br.com.fajbio.assistenciatecnica.domain.model;

import br.com.fajbio.assistenciatecnica.domain.enums.EQuoteEvent;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_quote_events", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_quoteevent_quote", columnList = "quote_id"),
        @Index(name = "idx_quoteevent_tipo", columnList = "tipo"),
        @Index(name = "idx_quoteevent_timestamp", columnList = "data_hora")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quote_id", nullable = false)
    private Long quoteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", insertable = false, updatable = false)
    private Quote quote;

    @Column(name = "tipo", length = 20)
    @Enumerated(EnumType.STRING)
    private EQuoteEvent tipo;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;
}