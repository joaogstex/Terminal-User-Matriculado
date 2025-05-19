package br.com.ecoground.user.user_ecoground.terminal.dto;

import lombok.Data;

@Data
public class NovoUsuarioRequest {
    private String userId;
    private String nome;
    private String senha;
    private Integer tipoUsuario;
    private Integer autoridade;
    private String validoDe;
    private String validoAte;

}
