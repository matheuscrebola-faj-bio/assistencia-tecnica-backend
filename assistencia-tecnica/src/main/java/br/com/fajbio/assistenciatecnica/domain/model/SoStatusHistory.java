package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_so_status_history", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_sostathist_so", columnList = "service_order_id"),
        @Index(name = "idx_sostathist_from", columnList = "from_status_id"),
        @Index(name = "idx_sostathist_to", columnList = "to_status_id"),
        @Index(name = "idx_sostathist_changed", columnList = "atualizado_em")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_order_id", nullable = false)
    private Long serviceOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", insertable = false, updatable = false)
    private ServiceOrder serviceOrder;

    @Column(name = "from_status_id")
    private Integer fromStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_status_id", insertable = false, updatable = false)
    private SoStatus fromStatus;

    @Column(name = "to_status_id", nullable = false)
    private Integer toStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_status_id", insertable = false, updatable = false)
    private SoStatus toStatus;

    @Column(name = "changed_by_user_id")
    private Long changedByUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by_user_id", insertable = false, updatable = false)
    private User changedBy;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}