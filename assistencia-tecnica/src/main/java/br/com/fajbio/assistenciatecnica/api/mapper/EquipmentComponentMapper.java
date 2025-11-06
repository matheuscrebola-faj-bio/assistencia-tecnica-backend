package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.EquipmentComponentRes;
import br.com.fajbio.assistenciatecnica.domain.model.Equipment;
import br.com.fajbio.assistenciatecnica.domain.model.EquipmentComponent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentComponentMapper {

    public EquipmentComponentRes mappear(EquipmentComponent component){
        return EquipmentComponentRes.builder()
                .id(component.getId())
                .quantidade(component.getQuantidade())
                .build();
    }

    public List<EquipmentComponentRes> mappear(Equipment equipment) {
        List<EquipmentComponent> components = equipment.getComponents();
        return components.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public EquipmentComponent mappear(Equipment equipment, br.com.fajbio.assistenciatecnica.domain.model.Component component, Byte quantidade) {
        return EquipmentComponent.builder()
                .equipmentId(equipment.getId())
                .equipment(equipment)
                .componentId(component.getId())
                .component(component)
                .quantidade(quantidade)
                .build();
    }
}
