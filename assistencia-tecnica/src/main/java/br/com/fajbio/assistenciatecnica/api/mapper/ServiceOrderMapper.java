package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceOrderReq;
import br.com.fajbio.assistenciatecnica.domain.enums.EOrigin;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import br.com.fajbio.assistenciatecnica.domain.model.ServiceOrder;
import br.com.fajbio.assistenciatecnica.domain.model.SoStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ServiceOrderMapper {

    public ServiceOrder mappear(ServiceOrderReq req, Customer customer, Equipment equipment, SoStatus status) {
        return ServiceOrder.builder()
                .customerId(customer.getId())
                .customer(customer)
                .equipmentId(equipment.getId())
                .equipment(equipment)
                .currentStatusId(status.getId())
                .currentStatus(status)
                .origin(EOrigin.WEB_FORM)
                .requesterContato(req.contato())
                .requesterEmail(req.email())
                .requesterCompanyName(req.empresa())
                .requesterAddress(req.endereco())
                .productLine(req.produto())
                .criadoEm(LocalDateTime.now())
                .build();
    }
}
