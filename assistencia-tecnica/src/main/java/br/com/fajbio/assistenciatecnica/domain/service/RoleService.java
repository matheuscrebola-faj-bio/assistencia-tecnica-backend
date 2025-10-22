package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.domain.model.Role;
import br.com.fajbio.assistenciatecnica.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public Set<Role> encontrarPeloNome(Set<String> roles) {
        return repository.encontrarTodosPeloNome(roles);
    }

    public Role encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Role> encontrarTodos() {
        return repository.findAll();
    }
}
