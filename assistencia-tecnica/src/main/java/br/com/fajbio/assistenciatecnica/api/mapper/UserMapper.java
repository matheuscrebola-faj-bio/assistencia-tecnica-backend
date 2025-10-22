package br.com.fajbio.assistenciatecnica.api.mapper;

import br.com.fajbio.assistenciatecnica.api.dto.UserReq;
import br.com.fajbio.assistenciatecnica.api.dto.UserRes;
import br.com.fajbio.assistenciatecnica.api.dto.UserUpdate;
import br.com.fajbio.assistenciatecnica.domain.model.Role;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserRes mappear(User user) {
        return UserRes.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

    public User mappear(UserReq req, Set<Role> roles){
        return User.builder()
                .username(req.username())
                .passwordHash(req.passwordHash())
                .email(req.email())
                .ativo(true)
                .roles(roles)
                .build();
    }

    public User mappear(User user, UserUpdate update) {
        return User.builder()
                .id(user.getId())
                .username(update.username())
                .passwordHash(update.passwordHash())
                .email(update.email())
                .ativo(user.getAtivo())
                .roles(user.getRoles())
                .build();
    }

    public User mappear(User user, Boolean ativo) {
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .passwordHash(user.getPasswordHash())
                .email(user.getEmail())
                .ativo(ativo)
                .roles(user.getRoles())
                .build();
    }

    public User mappear(Role role, User user){
        Set<Role> roles = user.getRoles();
        roles.add(role);
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .passwordHash(user.getPasswordHash())
                .email(user.getEmail())
                .ativo(user.getAtivo())
                .roles(roles)
                .build();
    }

    public User mappear(User user, Role role) {
        Set<Role> roles = user.getRoles();
        roles.remove(role);
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .passwordHash(user.getPasswordHash())
                .email(user.getEmail())
                .ativo(user.getAtivo())
                .roles(roles)
                .build();
    }
}
