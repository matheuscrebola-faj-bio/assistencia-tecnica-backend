package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentRes;
import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentMapper {
    private final EquipmentModelMapper equipmentModelMapper;

    public EquipmentRes mappear(Equipment equipment){
        return EquipmentRes.builder()
                .id(equipment.getId())
                .dataUltimaGarantia(equipment.getDataUltimaGarantia())
                .model(equipmentModelMapper.mappear(equipment.getModel()))
                .serial(equipment.getSerial())
                .build();
    }
}
