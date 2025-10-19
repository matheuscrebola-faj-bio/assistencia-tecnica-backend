package br.com.fajbio.assistenciatecnica.domain.model;

import br.com.fajbio.assistenciatecnica.domain.enums.EAssignmentStrategy;
import br.com.fajbio.assistenciatecnica.domain.enums.EOrigin;
import br.com.fajbio.assistenciatecnica.domain.enums.EProductLine;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_service_orders", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_so_customer", columnList = "customer_id"),
        @Index(name = "idx_so_equipment", columnList = "equipment_id"),
        @Index(name = "idx_so_status", columnList = "current_status_id"),
        @Index(name = "idx_so_assigned", columnList = "assigned_to_user_id"),
        @Index(name = "idx_so_created", columnList = "criado_em")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "equipment_id", nullable = false)
    private Long equipmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", insertable = false, updatable = false)
    private Equipment equipment;

    @Column(name = "current_status_id", nullable = false)
    private Integer currentStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_status_id", insertable = false, updatable = false)
    private SoStatus currentStatus;

    @Column(name = "assigned_to_user_id")
    private Long assignedToUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_user_id", insertable = false, updatable = false)
    private User assignedTo;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Enumerated(EnumType.STRING)
    @Column(name = "origem", nullable = false, length = 20)
    private EOrigin origin;

    @Column(name = "requester_name", nullable = false, length = 100)
    private String requesterName;

    @Column(name = "requester_email", nullable = false, length = 200)
    private String requesterEmail;

    @Column(name = "requester_company_name", nullable = false, length = 100)
    private String requesterCompanyName;

    @Column(name = "requester_document", nullable = false, length = 20)
    private String requesterDocument;

    @Column(name = "requester_address", nullable = false, length = 200)
    private String requesterAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_line", nullable = false, length = 20)
    private EProductLine productLine;

    @Column(name = "claimed_at")
    private LocalDateTime claimedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_strategy", nullable = false, length = 20)
    private EAssignmentStrategy assignmentStrategy;

    @OneToMany(mappedBy = "serviceOrder")
    private List<SoStatusHistory> statusHistory;

    @OneToMany(mappedBy = "serviceOrder")
    private List<Quote> quotes;

    @OneToMany(mappedBy = "serviceOrder")
    private List<WorkOrder> workOrders;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;
}