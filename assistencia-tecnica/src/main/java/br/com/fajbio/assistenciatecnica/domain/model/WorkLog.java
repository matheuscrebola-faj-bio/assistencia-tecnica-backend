package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_work_logs", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_worklog_wo", columnList = "work_order_id"),
        @Index(name = "idx_worklog_evento", columnList = "evento")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "work_order_id", nullable = false)
    private Long workOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_order_id", insertable = false, updatable = false)
    private WorkOrder workOrder;

    @Column(name = "evento", length = 100)
    private String evento;
}