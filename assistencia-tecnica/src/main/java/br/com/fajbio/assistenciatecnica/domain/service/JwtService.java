package br.com.fajbio.assistenciatecnica.domain.service;

import br.com.fajbio.assistenciatecnica.infra.jwt.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtConfig jwtConfig;
    private final SecretKey key;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String gerarAccessToken(Long usuarioId, String email, List<String> roles) {
        return Jwts.builder()
                .subject(usuarioId.toString())
                .claim("email", email)
                .claim("roles", roles)
                .claim("tipo", "access")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getAccessTokenExpiration()))
                .signWith(key)
                .compact();
    }

    public String gerarRefreshToken(Long usuarioId) {
        return Jwts.builder()
                .subject(usuarioId.toString())
                .claim("tipo", "refresh")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getRefreshTokenExpiration()))
                .signWith(key)
                .compact();
    }

    public Claims validarToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new InvalidTokenException("Token inválido ou expirado");
        }
    }

    public Long extrairUsuarioId(String token) {
        Claims claims = validarToken(token);
        return Long.parseLong(claims.getSubject());
    }

    public boolean isRefreshToken(String token) {
        Claims claims = validarToken(token);
        return "refresh".equals(claims.get("tipo"));
    }

    public Date extrairExpiracao(String token) {
        Claims claims = validarToken(token);
        return claims.getExpiration();
    }
}
