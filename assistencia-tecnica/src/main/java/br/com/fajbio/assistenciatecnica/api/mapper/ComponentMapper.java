package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.ComponentReq;
import br.com.fajbio.assistenciatecnica.api.dto.ComponentRes;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComponentMapper {

    public List<ComponentRes> mappear(List<br.com.fajbio.assistenciatecnica.domain.model.Component> components) {
        return components.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public ComponentRes mappear(br.com.fajbio.assistenciatecnica.domain.model.Component component){
        return ComponentRes.builder()
                .id(component.getId())
                .peca(component.getPeca())
                .unidade(component.getUnidade())
                .preco(component.getPreco())
                .build();
    }

    public br.com.fajbio.assistenciatecnica.domain.model.Component mappear(ComponentReq req) {
        return br.com.fajbio.assistenciatecnica.domain.model.Component.builder()
                .peca(req.peca())
                .unidade(req.unidade())
                .preco(req.preco())
                .build();
    }
}
