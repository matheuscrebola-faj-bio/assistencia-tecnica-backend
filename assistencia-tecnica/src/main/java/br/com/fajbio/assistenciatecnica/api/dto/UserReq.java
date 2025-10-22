package br.com.fajbio.assistenciatecnica.api.dto;

import java.util.Set;

public record UserReq(
        String username,
        String passwordHash,
        String email,
        Set<String> roles
) {
}
