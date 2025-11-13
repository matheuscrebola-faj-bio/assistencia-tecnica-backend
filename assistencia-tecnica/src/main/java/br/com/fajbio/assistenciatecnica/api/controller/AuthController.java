package br.com.fajbio.assistenciatecnica.api.controller;

import br.com.fajbio.assistenciatecnica.api.dto.AuthReq;
import br.com.fajbio.assistenciatecnica.api.dto.AuthRes;
import br.com.fajbio.assistenciatecnica.api.mapper.AccessLogMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.AuthMapper;
import br.com.fajbio.assistenciatecnica.api.mapper.UserMapper;
import br.com.fajbio.assistenciatecnica.domain.service.AccessLogService;
import br.com.fajbio.assistenciatecnica.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccessLogService accessLogService;
    private final AccessLogMapper accessLogMapper;
    private final AuthService authService;
    private final AuthMapper authMapper;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthRes> login(
        @RequestBody AuthReq req
        ){
        accessLogService.registrar(accessLogMapper.mappear(1L,"POST", "/auth/logout"));
        //TODO: valida credenciais, emite access/refresh token e retorna o usuário logado.
        var user = authService.realizarLogin(req);
        var userRes = userMapper.mappear(user);
        var auth = authMapper.mappear(userRes);
        return new ResponseEntity<>(auth, HttpStatus.OK);
    }

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
