package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.AuthReq;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import br.com.fajbio.assistenciatecnica.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;

    public User realizarLogin(AuthReq req){
        var user = repository.findByEmail(req.email());
        if (user.getPasswordHash().equals(req.password())){
            return user;
        }
        return null;
    }
}
