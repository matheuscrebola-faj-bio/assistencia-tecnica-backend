package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_shipment_events", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_shipevent_shipment", columnList = "shipment_id"),
        @Index(name = "idx_shipevent_timestamp", columnList = "data_hora"),
        @Index(name = "idx_shipevent_status", columnList = "shipment_status")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipment_id", nullable = false)
    private Long shipmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", insertable = false, updatable = false)
    private Shipment shipment;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "shipment_status", length = 100)
    private String status;

    @Column(name = "localizacao")
    private String localizacao;
}