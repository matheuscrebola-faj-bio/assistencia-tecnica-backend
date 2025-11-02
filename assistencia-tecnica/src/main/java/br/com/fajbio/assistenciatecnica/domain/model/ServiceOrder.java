package br.com.fajbio.assistenciatecnica.domain.model;

import br.com.fajbio.assistenciatecnica.domain.enums.EOrigin;
import br.com.fajbio.assistenciatecnica.domain.enums.EProductLine;
import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_service_orders", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_so_customer", columnList = "customer_id"),
        @Index(name = "idx_so_equipment", columnList = "equipment_id"),
        @Index(name = "idx_so_assigned", columnList = "assigned_to_user_id")
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

    @Column
    private String atendimento;

    @Column
    private Short ultimoValor;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable=false, updatable = false)
    private Customer customer;

    @Column(name = "equipment_id", nullable = false)
    private Long equipmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", insertable=false, updatable = false)
    private Equipment equipment;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ESoStatus currentStatus;

    @Column(name = "assigned_to_user_id")
    private Long assignedToUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_user_id", insertable=false, updatable=false)
    private User assignedTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "origem", nullable = false, length = 20)
    private EOrigin origin;

    @Column(name = "requester_contato", nullable = false, length = 100)
    private String requesterContato;

    @Column(name = "requester_email", nullable = false, length = 200)
    private String requesterEmail;

    @Column(name = "requester_company_name", nullable = false, length = 100)
    private String requesterCompanyName;

    @Column(name = "requester_address", nullable = false, length = 200)
    private String requesterAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_line", nullable = false, length = 20)
    private EProductLine productLine;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "serviceOrder")
    private List<SoStatusHistory> statusHistory;

    @OneToMany(mappedBy = "serviceOrder")
    private List<Quote> quotes;

    @OneToMany(mappedBy = "serviceOrder")
    private List<WorkOrder> workOrders;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Column
    private Boolean ativo;
}