package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_equipments", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_equipment_customer", columnList = "customer_id"),
        @Index(name = "idx_equipment_model", columnList = "model_id"),
        @Index(name = "idx_equipment_serial", columnList = "serial", unique = true),
        @Index(name = "idx_equipment_garantia", columnList = "data_ultima_garantia")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "model_id", nullable = false)
    private Long modelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", insertable = false, updatable = false)
    private EquipmentModel model;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipmentComponent> components;

    @Column(name = "serial", unique = true, length = 100)
    private String serial;

    @Column(name = "data_ultima_garantia")
    private LocalDate dataUltimaGarantia;
}