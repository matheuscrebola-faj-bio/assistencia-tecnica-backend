package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_checklist_catalog_items", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_chkcatitem_catalog", columnList = "checklist_catalog_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChecklistCatalogItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "checklist_catalog_id", nullable = false)
    private Long checklistCatalogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checklist_catalog_id", insertable = false, updatable = false)
    private ChecklistCatalog checklistCatalog;

    @Column(name = "etiqueta")
    private String etiqueta;

    @Column(name = "tipo", length = 50)
    private String tipo;
}