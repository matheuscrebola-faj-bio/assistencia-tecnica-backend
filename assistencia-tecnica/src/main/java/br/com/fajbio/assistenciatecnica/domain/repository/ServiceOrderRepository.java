package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder,Long> {

    @Query("SELECT MAX(a.valor) FROM Atendimento a WHERE a.ano = :ano AND a.mes = :mes")
    Short findUltimoValorOrderByUltimoValorDesc(@Param("ano") int ano, @Param("mes") int mes);
}
