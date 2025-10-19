package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_checklists", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_checklist_so", columnList = "service_order_id"),
        @Index(name = "idx_checklist_catalog", columnList = "checklist_catalog_id"),
        @Index(name = "idx_checklist_preenchido", columnList = "preenchido_em")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_order_id", nullable = false)
    private Long serviceOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", insertable = false, updatable = false)
    private ServiceOrder serviceOrder;

    @Column(name = "checklist_catalog_id", nullable = false)
    private Long checklistCatalogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checklist_catalog_id", insertable = false, updatable = false)
    private ChecklistCatalog checklistCatalog;

    @Column(name = "preenchido_por_user_id")
    private Long preenchidoPorUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preenchido_por_user_id", insertable = false, updatable = false)
    private User preenchidoPor;

    @Column(name = "preenchido_em")
    private LocalDateTime preenchidoEm;

    @OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChecklistItem> items;
}