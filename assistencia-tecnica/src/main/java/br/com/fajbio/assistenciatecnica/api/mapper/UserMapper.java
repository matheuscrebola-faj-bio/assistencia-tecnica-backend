package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.UserReq;
import br.com.fajbio.assistenciatecnica.api.dto.UserRes;
import br.com.fajbio.assistenciatecnica.domain.model.Role;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserRes mappear(User user) {
        return UserRes.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(Role::getNome)
                        .collect(Collectors.toList()))
                .build();
    }

    public User mappear(UserReq req, Set<Role> roles){
        return User.builder()
                .username(req.username())
                .passwordHash(req.password())
                .email(req.email())
                .ativo(true)
                .roles(roles)
                .build();
    }

}
