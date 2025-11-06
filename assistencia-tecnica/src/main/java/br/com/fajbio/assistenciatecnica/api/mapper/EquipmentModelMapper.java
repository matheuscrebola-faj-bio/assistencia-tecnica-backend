package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentModelReq;
import br.com.fajbio.assistenciatecnica.api.dto.EquipmentModelRes;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentModel;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EquipmentModelMapper {

    public EquipmentModelRes mappear(EquipmentModel model){
        return EquipmentModelRes.builder()
                .id(model.getId())
                .fabricante(model.getFabricante())
                .modelo(model.getModelo())
                .build();
    }

    public List<EquipmentModelRes> mappear(List<EquipmentModel> equipmentModels) {
        return equipmentModels.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public EquipmentModel mappear(EquipmentModelReq req, EquipmentType type) {
        return EquipmentModel.builder()
                .typeId(type.getId())
                .type(type)
                .fabricante(req.fabricante())
                .modelo(req.modelo())
                .build();
    }
}
