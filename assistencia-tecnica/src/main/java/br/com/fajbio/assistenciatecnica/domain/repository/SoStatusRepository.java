package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.domain.enums.ESoStatus;
import br.com.fajbio.assistenciatecnica.domain.model.SoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoStatusRepository extends JpaRepository<SoStatus,Long> {
    SoStatus findByNome(ESoStatus eSoStatus);
}
