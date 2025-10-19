package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.domain.model.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentModelRepository extends JpaRepository<EquipmentModel,Long> {
}
