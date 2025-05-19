package br.com.ecoground.user.user_ecoground.terminal.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ecoground.user.user_ecoground.terminal.model.UsuarioIntelbras;

public class UsuarioIntelbrasDTO {
    @JsonProperty("UserList")
    private List<UsuarioIntelbras> usuarios;

    public UsuarioIntelbrasDTO(List<UsuarioIntelbras> usuarios) {
        this.usuarios = usuarios;
    }

    public List<UsuarioIntelbras> getUsuarios() {
        return usuarios;
    }
}
