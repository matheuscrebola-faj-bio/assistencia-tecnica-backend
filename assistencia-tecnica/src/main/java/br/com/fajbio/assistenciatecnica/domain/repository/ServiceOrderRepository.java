package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder,Long> {
    @Query("""
           select coalesce(max(s.ultimoValor), 0)
           from ServiceOrder s
           where s.criadoEm >= :inicioMes and s.criadoEm < :inicioProxMes
           """)
    Short findMaxUltimoValorNoMes(@Param("inicioMes") LocalDateTime inicioMes,
                                  @Param("inicioProxMes") LocalDateTime inicioProxMes);

    List<ServiceOrder> findByCurrentStatus(ESoStatus eSoStatus);
}
