package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_addresses", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_address_cep", columnList = "cep"),
        @Index(name = "idx_address_cidade_uf", columnList = "cidade, uf")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "cep", length = 10)
    private String cep;

    @Column(name = "pais", length = 100)
    private String pais;
}