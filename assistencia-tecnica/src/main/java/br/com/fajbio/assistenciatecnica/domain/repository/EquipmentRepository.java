package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findByCustomerId(Long id);
}
