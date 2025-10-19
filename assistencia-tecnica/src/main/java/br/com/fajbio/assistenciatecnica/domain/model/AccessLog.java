package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_access_logs", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_accesslog_user", columnList = "user_id"),
        @Index(name = "idx_accesslog_timestamp", columnList = "data_hora"),
        @Index(name = "idx_accesslog_endpoint", columnList = "endpoint")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "metodo", length = 10)
    private String metodo;

    @Column(name = "endpoint", length = 200)
    private String endpoint;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
}