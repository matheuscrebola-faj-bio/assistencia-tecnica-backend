package br.com.fajbio.assistenciatecnica.api.dto;

import br.com.fajbio.assistenciatecnica.domain.model.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public class UserRes {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
}
