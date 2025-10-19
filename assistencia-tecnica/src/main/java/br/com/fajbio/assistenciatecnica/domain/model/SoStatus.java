package br.com.fajbio.assistenciatecnica.domain.model;

import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_so_status", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_sostatus_codigo", columnList = "codigo", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", unique = true, nullable = false, length = 50)
    private String codigo;

    @Column(name = "nome", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private ESoStatus nome;
}