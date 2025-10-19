package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.domain.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    @Query("SELECT al FROM AccessLog al WHERE al.dataHora < :dataLimite")
    List<AccessLog> buscarRegistrosAnterioresA30Dias(@Param("dataLimite") LocalDateTime dataLimite);

}
