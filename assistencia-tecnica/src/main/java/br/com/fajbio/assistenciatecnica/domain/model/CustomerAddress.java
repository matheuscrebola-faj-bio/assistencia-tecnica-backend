package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_customer_addresses", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_custaddr_customer", columnList = "customer_id"),
        @Index(name = "idx_custaddr_address", columnList = "address_id"),
        @Index(name = "idx_custaddr_tipo", columnList = "tipo")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "address_id", nullable = false)
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private Address address;

    @Column(name = "tipo", length = 50)
    private String tipo;
}