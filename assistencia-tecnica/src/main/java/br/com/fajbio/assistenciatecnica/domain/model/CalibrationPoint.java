package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_calibration_points", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_calpoint_calibration", columnList = "calibration_id"),
        @Index(name = "idx_calpoint_grandeza", columnList = "grandeza")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalibrationPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calibration_id", nullable = false)
    private Long calibrationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calibration_id", insertable = false, updatable = false)
    private Calibration calibration;

    @Column(name = "grandeza", length = 100)
    private String grandeza;

    @Column(name = "valor_nominal", precision = 15, scale = 6)
    private BigDecimal valorNominal;

    @Column(name = "valor_medido", precision = 15, scale = 6)
    private BigDecimal valorMedido;

    @Column(name = "incerteza", precision = 15, scale = 6)
    private BigDecimal incerteza;
}