package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.UserRes;
import br.com.fajbio.assistenciatecnica.api.dto.UserUpdate;
import br.com.fajbio.assistenciatecnica.domain.model.Role;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import br.com.fajbio.assistenciatecnica.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional
    protected User salvar(User user){
        return repository.save(user);
    }

    public User encontrarPeloId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<UserRes> encontrarTodos() {
        return repository.encontrarTodos();
    }

    public User cadastrar(User user) {
        return salvar(user);
    }

    @Transactional
    public void atualizar(Long userId, UserUpdate update) {
        User user = encontrarPeloId(userId);
        user.setUsername(update.username());
        user.setPasswordHash(update.passwordHash());
        user.setEmail(update.email());
    }

    @Transactional
    public void delecaoLogica(Long userId) {
        User user = encontrarPeloId(userId);
        user.setAtivo(false);
    }

    @Transactional
    public void adicionarRoleAoUsuario(Role role, Long userId) {
        User user = encontrarPeloId(userId);
        user.getRoles().add(role);
    }

    @Transactional
    public void removerRoleDoUsuario(Long userId, Long roleId) {
        User user = encontrarPeloId(userId);
        boolean removed = user.getRoles().removeIf(r -> r.getId().equals(roleId));
        if (!removed) {
            throw new RuntimeException("Role não encontrada para este usuário");
        }
    }
}
