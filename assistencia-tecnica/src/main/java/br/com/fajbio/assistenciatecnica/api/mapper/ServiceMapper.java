package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.ServiceReq;
import br.com.fajbio.assistenciatecnica.api.dto.ServiceRes;
import br.com.fajbio.assistenciatecnica.domain.model.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    public List<ServiceRes> mappear(List<Service> services) {
        return services.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public ServiceRes mappear(Service service){
        return ServiceRes.builder()
                .id(service.getId())
                .nome(service.getNome())
                .precoBase(service.getPrecoBase())
                .build();
    }

    public Service mappear(ServiceReq req) {
        return Service.builder()
                .nome(req.nome())
                .precoBase(req.precoBase())
                .build();
    }
}
