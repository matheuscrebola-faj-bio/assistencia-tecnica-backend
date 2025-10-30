package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentModelRes;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentModelMapper {
    private final EquipmentTypeMapper equipmentTypeMapper;

    public EquipmentModelRes mappear(EquipmentModel model){
        return EquipmentModelRes.builder()
                .id(model.getId())
                .type(equipmentTypeMapper.mappear(model.getType()))
                .fabricante(model.getFabricante())
                .modelo(model.getModelo())
                .build();
    }
}
