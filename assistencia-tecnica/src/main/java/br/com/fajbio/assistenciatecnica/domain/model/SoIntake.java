package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_so_intake", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_intake_so", columnList = "service_order_id"),
        @Index(name = "idx_intake_data", columnList = "data_chegada")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_order_id", nullable = false)
    private Long serviceOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", insertable = false, updatable = false)
    private ServiceOrder serviceOrder;

    @Column(name = "data_chegada")
    private LocalDate dataChegada;

    @Column(name = "lacre_intacto")
    private Boolean lacreIntacto;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;
}