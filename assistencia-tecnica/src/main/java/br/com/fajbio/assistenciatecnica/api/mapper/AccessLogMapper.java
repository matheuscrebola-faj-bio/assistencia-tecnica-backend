package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.domain.model.AccessLog;
import br.com.fajbio.assistenciatecnica.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AccessLogMapper {
    private final UserService service;

    public AccessLog mappear(Long userId, String metodo, String endpoint) {
        var user = service.encontrarPeloId(userId);
        return AccessLog.builder()
                .userId(user.getId())
                .user(user)
                .metodo(metodo)
                .endpoint(endpoint)
                .dataHora(LocalDateTime.now())
                .build();
    }

}
