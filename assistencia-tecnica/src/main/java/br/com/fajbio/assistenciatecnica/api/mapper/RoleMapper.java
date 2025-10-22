package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.RoleRes;
import br.com.fajbio.assistenciatecnica.domain.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    public List<RoleRes> mappear(List<Role> roles) {
        return roles.stream()
                .map(this::mappear)
                .collect(Collectors.toList());
    }

    public RoleRes mappear(Role role){
        return RoleRes.builder()
                .id(role.getId())
                .nome(role.getNome())
                .build();
    }
}
