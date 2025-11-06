package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentReq;
import br.com.fajbio.assistenciatecnica.api.dto.EquipmentRes;
import br.com.fajbio.assistenciatecnica.domain.model.Customer;
import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EquipmentMapper {

    public EquipmentRes mappear(Equipment equipment){
        return EquipmentRes.builder()
                .id(equipment.getId())
                .serial(equipment.getSerial())
                .build();
    }

    public List<EquipmentRes> mappear(List<Equipment> equipments) {
        return equipments.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public Equipment mappear(EquipmentReq req, EquipmentModel model, Customer customer) {
        return Equipment.builder()
                .customerId(customer.getId())
                .customer(customer)
                .modelId(model.getId())
                .model(model)
                .serial(req.serial())
                .dataUltimaGarantia(req.dataUltimaGarantia())
                .build();
    }
}
