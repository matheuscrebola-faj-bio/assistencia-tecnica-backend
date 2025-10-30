package br.com.fajbio.assistenciatecnica.api.dto;

import lombok.Builder;
import java.util.List;

@Builder
public class UserRes {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
