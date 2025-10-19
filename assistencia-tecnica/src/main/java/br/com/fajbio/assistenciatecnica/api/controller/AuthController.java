package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(){
//        accessLogService.registrar(accessLogMapper.mappear(1L,"POST", "/auth/logout"));
//        //TODO: valida credenciais, emite access/refresh token e retorna o usuário logado.
//        return null;
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/auth/logout"));
//        //TODO: invalida o token/refresh ativo e encerra a sessão.
//        return null;
//    }
//
//    @PostMapping("/refresh")
//    public ResponseEntity<?> refreshToken(@RequestHeader Long id){
//        accessLogService.registrar(accessLogMapper.mappear(id, "POST", "/auth/refresh"));
//        //TODO: valida refresh token e emite novo access token.
//        return null;
//    }
}
