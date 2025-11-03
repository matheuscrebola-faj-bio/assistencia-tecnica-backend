package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.api.dto.WorkLogRes;
import br.com.fajbio.assistenciatecnica.domain.model.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog,Long> {
    List<WorkLogRes> findAllByWorkOrderId(Long workOrderId);
}
