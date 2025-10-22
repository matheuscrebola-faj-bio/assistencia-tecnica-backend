package br.com.fajbio.assistenciatecnica.domain.service;

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

    public List<User> encontrarTodos() {
        return repository.findAll();
    }

    public User cadastrar(User user) {
        return salvar(user);
    }

    public User atualizar(User user) {
        return salvar(user);
    }

    public User delecaoLogica(User user) {
        return salvar(user);
    }

    public User adicionarRoleAoUsuario(User user) {
        return salvar(user);
    }

    public User removerRoleDoUsuario(User user) {
        return salvar(user);
    }
}
