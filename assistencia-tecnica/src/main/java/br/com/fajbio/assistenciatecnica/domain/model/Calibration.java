package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_calibrations", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_calibration_so", columnList = "service_order_id"),
        @Index(name = "idx_calibration_tecnico", columnList = "tecnico_id"),
        @Index(name = "idx_calibration_data", columnList = "data_calibracao"),
        @Index(name = "idx_calibration_validade", columnList = "validade")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calibration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_order_id", nullable = false)
    private Long serviceOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", insertable = false, updatable = false)
    private ServiceOrder serviceOrder;

    @Column(name = "tecnico_id", nullable = false)
    private Long tecnicoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id", insertable = false, updatable = false)
    private User tecnico;

    @Column(name = "data_calibracao")
    private LocalDate data;

    @Column(name = "referencia_certificado", length = 100)
    private String referenciaCertificado;

    @Column(name = "validade")
    private LocalDate validade;

    @OneToMany(mappedBy = "calibration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalibrationPoint> points;
}