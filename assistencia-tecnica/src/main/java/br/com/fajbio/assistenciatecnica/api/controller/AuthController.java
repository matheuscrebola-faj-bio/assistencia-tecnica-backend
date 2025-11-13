package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.AuthRes;
import br.com.fajbio.assistenciatecnica.api.dto.LoginReq;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

    private final AuthService authService;
//    private final AccessLogService accessLogService;
//    private final AccessLogMapper accessLogMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthRes> login(@Valid @RequestBody LoginReq request) {
        accessLogService.registrar(
                accessLogMapper.mappear(null, "POST", "/auth/login")
        );

        AuthRes response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader("Authorization") String token,
            @RequestAttribute("usuarioId") Long usuarioId) {

        accessLogService.registrar(
                accessLogMapper.mappear(usuarioId, "POST", "/auth/logout")
        );

        authService.logout(token);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthRes> refreshToken(
            @RequestHeader("Authorization") String refreshToken,
            @RequestAttribute("usuarioId") Long usuarioId) {

        accessLogService.registrar(
                accessLogMapper.mappear(usuarioId, "POST", "/auth/refresh")
        );

        AuthRes response = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(response);
    }
}
