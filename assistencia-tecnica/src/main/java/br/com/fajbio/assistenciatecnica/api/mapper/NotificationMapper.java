package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrderReq;
import br.com.fajbio.assistenciatecnica.domain.model.Notification;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public Notification mappear(ServiceOrder service, ServiceOrderReq req) {
        return Notification.builder()
                .serviceOrder(service)
                .serviceOrderId(service.getId())
                .tipo("email")
                .template("template")
                .destinatario(req.email())
                .enviadoEm(LocalDateTime.now())
                .build();
    }
}
