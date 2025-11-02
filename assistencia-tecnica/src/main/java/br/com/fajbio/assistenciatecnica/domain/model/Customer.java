package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Table(name = "tb_customers", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_customer_documento", columnList = "documento"),
        @Index(name = "idx_customer_ativo", columnList = "ativo")
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_legal", nullable = false)
    private String nomeLegal;

    @Column(name = "documento", unique = true, nullable = false, length = 20)
    private String documento;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerContact> contacts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerAddress> addresses;

    @OneToMany(mappedBy = "customer")
    private List<ServiceOrder> serviceOrders;
}