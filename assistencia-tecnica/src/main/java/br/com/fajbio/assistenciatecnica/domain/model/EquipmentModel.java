package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_equipment_models", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_equipmodel_type", columnList = "type_id"),
        @Index(name = "idx_equipmodel_fabricante", columnList = "fabricante"),
        @Index(name = "idx_equipmodel_modelo", columnList = "modelo")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private EquipmentType type;

    @Column(name = "fabricante", length = 100)
    private String fabricante;

    @Column(name = "modelo", length = 100)
    private String modelo;
}