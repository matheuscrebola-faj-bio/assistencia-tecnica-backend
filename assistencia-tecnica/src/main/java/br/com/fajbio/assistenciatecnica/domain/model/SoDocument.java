package br.com.fajbio.assistenciatecnica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_so_documents", schema = "assistencia_tecnica", indexes = {
        @Index(name = "idx_sodoc_so", columnList = "service_order_id"),
        @Index(name = "idx_sodoc_tipo", columnList = "tipo_doc"),
        @Index(name = "idx_sodoc_created", columnList = "criado_em")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SoDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_order_id", nullable = false)
    private Long serviceOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_order_id", insertable = false, updatable = false)
    private ServiceOrder serviceOrder;

    @Column(name = "tipo_doc", length = 50)
    private String tipoDoc;

    @Column(name = "file_path", length = 500)
    private String filePath;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
}