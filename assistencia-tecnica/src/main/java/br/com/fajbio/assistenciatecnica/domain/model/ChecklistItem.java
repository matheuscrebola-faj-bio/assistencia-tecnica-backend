package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_checklist_items", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_chkitem_checklist", columnList = "checklist_id"),
        @Index(name = "idx_chkitem_catalog", columnList = "catalog_item_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChecklistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "checklist_id", nullable = false)
    private Long checklistId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checklist_id", insertable = false, updatable = false)
    private Checklist checklist;

    @Column(name = "catalog_item_id", nullable = false)
    private Long catalogItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_item_id", insertable = false, updatable = false)
    private ChecklistCatalogItem catalogItem;

    @Column(name = "valor", length = 500)
    private String valor;
}