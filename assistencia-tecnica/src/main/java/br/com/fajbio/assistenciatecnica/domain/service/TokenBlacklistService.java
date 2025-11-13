package br.com.fajbio.assistenciatecnica.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {
    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();
    private final Map<String, Long> tokenExpiracao = new ConcurrentHashMap<>();

    @Scheduled(fixedRate = 3600000) // Limpa a cada hora
    public void limparTokensExpirados() {
        long agora = System.currentTimeMillis();
        tokenExpiracao.entrySet().removeIf(entry -> {
            if (entry.getValue() < agora) {
                blacklistedTokens.remove(entry.getKey());
                return true;
            }
            return false;
        });
    }

    public void adicionarNaBlacklist(String token, Date expiracao) {
        blacklistedTokens.add(token);
        tokenExpiracao.put(token, expiracao.getTime());
    }

    public boolean estaNaBlacklist(String token) {
        return blacklistedTokens.contains(token);
    }
}
