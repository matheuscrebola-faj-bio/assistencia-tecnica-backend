package br.com.fajbio.assistenciatecnica.domain.model;

import br.com.fajbio.assistenciatecnica.domain.enums.EShipmentMode;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_shipments", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_shipment_so", columnList = "service_order_id"),
        @Index(name = "idx_shipment_tracking", columnList = "codigo_rastreio"),
        @Index(name = "idx_shipment_envio", columnList = "data_envio")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_order_id", nullable = false)
    private Long serviceOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", insertable = false, updatable = false)
    private ServiceOrder serviceOrder;

    @Column(name = "transportadora", length = 100)
    private String transportadora;

    @Column(name = "codigo_rastreio", length = 100)
    private String codigoRastreio;

    @Column(name = "data_envio")
    private LocalDate dataEnvio;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode", nullable = false, length = 20)
    private EShipmentMode mode;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShipmentEvent> events;
}