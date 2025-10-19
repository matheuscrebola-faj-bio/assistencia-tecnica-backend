package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notifications", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_notif_so", columnList = "service_order_id"),
        @Index(name = "idx_notif_tipo", columnList = "tipo"),
        @Index(name = "idx_notif_status", columnList = "notification_status"),
        @Index(name = "idx_notif_enviado", columnList = "enviado_em")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_order_id", nullable = false)
    private Long serviceOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", insertable = false, updatable = false)
    private ServiceOrder serviceOrder;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "template", length = 100)
    private String template;

    @Column(name = "destinatario")
    private String destinatario;

    @Column(name = "enviado_em")
    private LocalDateTime enviadoEm;

    @Column(name = "notification_status", length = 50)
    private String status;
}