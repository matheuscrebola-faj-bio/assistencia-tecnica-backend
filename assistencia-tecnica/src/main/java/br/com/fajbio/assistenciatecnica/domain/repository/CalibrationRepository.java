package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.api.dto.CalibrationPointRes;
import br.com.fajbio.assistenciatecnica.domain.model.Calibration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalibrationRepository extends JpaRepository<Calibration, Long> {

    @Query("SELECT new br.com.fajbio.assistenciatecnica.api.dto.CalibrationPointRes(" +
            "cp.id, cp.grandeza, cp.valorNominal, cp.valorMedido, cp.incerteza) " +
            "FROM Calibration c JOIN c.points cp WHERE c.id = :calibrationId")
    List<CalibrationPointRes> buscarPontosDeCalibracaoPeloId(@Param("calibrationId") Long calibrationId);
}
