package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentTypeReq;
import br.com.fajbio.assistenciatecnica.api.dto.EquipmentTypeRes;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentTypeMapper {

    public EquipmentTypeRes mappear(EquipmentType type){
        return EquipmentTypeRes.builder()
                .id(type.getId())
                .nome(type.getNome())
                .build();
    }

    public EquipmentType mappear(EquipmentTypeReq type) {
        return EquipmentType.builder()
                .nome(type.nome())
                .build();
    }

    public List<EquipmentTypeRes> mappear(List<EquipmentType> equipmentTypes) {
        return equipmentTypes.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }
}
