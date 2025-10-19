package br.com.fajbio.assistenciatecnica.infra.scheduler;

import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccessLogScheduler {
    private final AccessLogService service;

    @Scheduled(cron = "0 0 21 ? * SUN")
    private void limparDadosUltimos30Dias(){
        service.deletarRegistrosAnterioresA30Dias(
                service.buscarRegistrosAnterioresA30Dias(LocalDateTime.now().minusDays(30)));
    }
}