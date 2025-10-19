package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_components", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_component_peca", columnList = "peca")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "peca", nullable = false)
    private String peca;

    @Column(name = "unidade")
    private Short unidade;

    @Column(name = "preco", precision = 10, scale = 2)
    private BigDecimal preco;
}