package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.CalibrationPointReq;
import br.com.fajbio.assistenciatecnica.api.dto.CalibrationPointRes;
import br.com.fajbio.assistenciatecnica.api.dto.CalibrationReq;
import br.com.fajbio.assistenciatecnica.api.dto.CalibrationRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.CalibrationMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.CalibrationPointMapper;
import br.com.fajbio.assistenciatecnica.domain.model.Calibration;
import br.com.fajbio.assistenciatecnica.domain.model.CalibrationPoint;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.CalibrationPointService;
import br.com.fajbio.assistenciatecnica.domain.service.CalibrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calibrations")
@RequiredArgsConstructor
public class CalibrationsController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final CalibrationService calibrationService;
    private final CalibrationMapper calibrationMapper;
    private final CalibrationPointService calibrationPointService;
    private final CalibrationPointMapper calibrationPointMapper;

    @GetMapping("/{calibrationId}")
    public ResponseEntity<CalibrationRes> getCalibration(@RequestHeader Long userId, @RequestParam Long calibrationId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/calibrations/id"));
        //detalhe da calibração.
        CalibrationRes res = calibrationMapper.mapear(calibrationService.encontrarPeloId(calibrationId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{calibrationId}")
    public ResponseEntity<?> updateCalibration(@RequestHeader Long userId, @RequestParam Long calibrationId, @RequestBody CalibrationReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "PUT", "/calibrations/id"));
        //atualiza calibração (datas/certificado).
        Calibration calibration = calibrationService.encontrarPeloId(calibrationId);
        calibrationService.atualizar(calibrationMapper.mapear(req, calibration));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{calibrationId}")
    public ResponseEntity<?> deleteCalibration(@RequestHeader Long userId, @RequestParam Long calibrationId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "DELETE", "/calibrations/id"));
        //remove calibração.
        calibrationService.deletarPeloId(calibrationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{calibrationId}/points")
    public ResponseEntity<List<CalibrationPointRes>> listCalibrationPoints(@RequestHeader Long userId, @RequestParam Long calibrationId){
        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/calibrations/id/points"));
        //lista pontos medidos.
        var res = calibrationService.buscarPontosPorId(calibrationId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{calibrationId}/points")
    public ResponseEntity<CalibrationPointRes> createCalibrationPoint(
            @RequestHeader Long userId,
            @RequestParam Long calibrationId,
            @RequestBody CalibrationPointReq req){
        accessLogService.registrar(accessLogMapper.mappear(userId, "POST", "/calibrations/id/points"));

        var cali = calibrationService.encontrarPeloId(calibrationId);
        var pointMap = calibrationPointMapper.mapear(req, cali);
        var point = calibrationPointService.criar(pointMap);
        var response = calibrationPointMapper.mapear(point); // converte para DTO de resposta

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping("/{calibrationId}/points/{pointId}")
//    public ResponseEntity<?> getCalibrationPoint(@RequestHeader Long userId,
//                                                 @RequestParam Long calibrationId,
//                                                 @RequestParam Long pointId){
//        accessLogService.registrar(accessLogMapper.mappear(userId, "GET", "/calibrations/id/points/{pointId}"));
//        //TODO: detalhe do ponto.
//
//        return null;
//    }
//
//    @PutMapping("/{calibrationId}/points/{pointId}")
//    public ResponseEntity<?> updateCalibrationPoint(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "PUT", "/calibrations/id/points/{pointId}"));
//        //TODO: atualiza ponto.
//        return null;
//    }
//
//    @DeleteMapping("/{calibrationId}/points/{pointId}")
//    public ResponseEntity<?> deleteCalibrationPoint(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "DELETE", "/calibrations/id/points/{pointId}"));
//        //TODO: remove ponto.
//        return null;
//    }
}
