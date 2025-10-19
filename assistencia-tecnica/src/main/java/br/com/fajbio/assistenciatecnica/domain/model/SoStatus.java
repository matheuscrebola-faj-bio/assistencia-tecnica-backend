package br.com.fajbio.assistenciatecnica.domain.model;

import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_so_status", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_sostatus_nome", columnList = "nome")
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

    @Column(name = "nome", nullable = false, length = 25)
    @Enumerated(EnumType.STRING)
    private ESoStatus nome;
}