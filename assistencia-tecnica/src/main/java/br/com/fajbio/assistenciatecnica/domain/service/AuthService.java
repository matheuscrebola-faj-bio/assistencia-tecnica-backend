package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.api.dto.AuthRes;
import br.com.fajbio.assistenciatecnica.api.dto.LoginReq;
import br.com.fajbio.assistenciatecnica.api.dto.UserRes;
import br.com.fajbio.assistenciatecnica.domain.model.Role;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import br.com.fajbio.assistenciatecnica.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenBlacklistService blacklistService;

    public AuthRes login(LoginReq request) {
        User usuario = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("Credenciais inválidas"));

        if (!passwordEncoder.matches(request.senha(), usuario.getPasswordHash())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        if (!usuario.isAtivo()) {
            throw new DisabledAccountException("Conta desativada");
        }

        List<String> roles = usuario.getRoles().stream()
                .map(Role::getNome)
                .toList();

        String accessToken = jwtService.gerarAccessToken(usuario.getId(), usuario.getEmail(), roles);
        String refreshToken = jwtService.gerarRefreshToken(usuario.getId());

        UserRes usuarioResponse = new UserRes(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                roles
        );

        return new AuthRes(accessToken, refreshToken, "Bearer", usuarioResponse);
    }

    public void logout(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new InvalidTokenException("Token ausente ou inválido");
        }

        String jwt = token.substring(7);

        // Valida o token antes de adicionar na blacklist
        jwtService.validarToken(jwt);

        // Adiciona na blacklist
        Date expiracao = jwtService.extrairExpiracao(jwt);
        blacklistService.adicionarNaBlacklist(jwt, expiracao);
    }

    public AuthRes refreshToken(String refreshToken) {
        if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
            throw new InvalidTokenException("Refresh token ausente ou inválido");
        }

        String jwt = refreshToken.substring(7);

        // Verifica se está na blacklist
        if (blacklistService.estaNaBlacklist(jwt)) {
            throw new InvalidTokenException("Token foi invalidado");
        }

        // Valida e verifica se é refresh token
        if (!jwtService.isRefreshToken(jwt)) {
            throw new InvalidTokenException("Token fornecido não é um refresh token");
        }

        Long usuarioId = jwtService.extrairUsuarioId(jwt);

        User usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        if (!usuario.getAtivo()) {
            throw new DisabledAccountException("Conta desativada");
        }

        List<String> roles = usuario.getRoles().stream()
                .map(Role::getNome)
                .toList();

        // Gera apenas novo access token
        String novoAccessToken = jwtService.gerarAccessToken(usuario.getId(), usuario.getEmail(), roles);

        UserRes usuarioResponse = new UserRes(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getUsername(),
                roles
        );

        return new AuthRes(novoAccessToken, jwt, "Bearer", usuarioResponse);
    }
}
