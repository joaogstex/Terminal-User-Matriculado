package br.com.ecoground.user.user_ecoground.terminal.service;

import br.com.ecoground.user.user_ecoground.terminal.model.UsuarioIntelbras;

public interface UsuarioService {
    public void enviarParaTerminal(UsuarioIntelbras usuario) throws Exception;
    public void cadastrarNovoUsuario(String userId, String nome, String senha, Integer userType, Integer authority, String validFrom, String validTo) throws Exception;
    public void deletarDoTerminal(String matricula) throws Exception;
    public String consultarUsuarioNoTerminal(String userId) throws Exception;
}
