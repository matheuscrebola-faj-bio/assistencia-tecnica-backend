package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_initial_tests", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_initest_so", columnList = "service_order_id"),
        @Index(name = "idx_initest_tecnico", columnList = "tecnico_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InitialTest {
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

    @Column(length = 100)
    private String aparelho;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column
    private String valores;

    @Column(name = "resultado")
    private String resultado;

    @Column
    private Boolean ativo;
}