package br.com.ecoground.user.user_ecoground.terminal.service.impl;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecoground.user.user_ecoground.terminal.dto.UsuarioIntelbrasDTO;
import br.com.ecoground.user.user_ecoground.terminal.model.UsuarioIntelbras;
import br.com.ecoground.user.user_ecoground.terminal.service.UsuarioService;
import br.com.ecoground.user.user_ecoground.terminal.util.DigestAuthUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private static final String IP = "192.168.1.49";
    //insertMulti
    private static final String POST_URL = "http://" + IP + "/cgi-bin/AccessUser.cgi?action=insertMulti";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "Ec0ground";

    public void enviarParaTerminal(UsuarioIntelbras usuario) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new UsuarioIntelbrasDTO(List.of(usuario)));
        System.out.println("[JSON ENVIADO] " + json);
        HttpResponse<String> response = DigestAuthUtil.sendWithDigestAuth(POST_URL, "POST", json, USERNAME, PASSWORD);
        System.out.println("[ENVIO TERMINAL] Status: " + response.statusCode());
        System.out.println("[ENVIO TERMINAL] Body: " + response.body());
    }

    public void cadastrarNovoUsuario(String userId, String nome, String senha, Integer userType, Integer authority, String validFrom, String validTo) throws Exception {
        UsuarioIntelbras novoUsuario = new UsuarioIntelbras();
        novoUsuario.setUserID(userId);
        novoUsuario.setName(nome);
        novoUsuario.setPassword(senha);
        novoUsuario.setUserType(userType);
        novoUsuario.setAuthority(authority);
        novoUsuario.setValidFrom(validFrom);
        novoUsuario.setValidTo(validTo);
        
        System.out.println("[CADASTRO NOVO USUÁRIO] " + userId + " - " + nome + " - " + senha + " - " + userType + " - " + authority + " - " + validFrom + " - " + validTo);
        enviarParaTerminal(novoUsuario);
    }       

    public void deletarDoTerminal(String matricula) throws Exception {
        String url = "http://" + IP + "/cgi-bin/AccessUser.cgi?action=removeMulti&UserIDList[0]=" + matricula;
        HttpResponse<String> response = DigestAuthUtil.sendWithDigestAuth(url, "GET", null, USERNAME, PASSWORD);
        System.out.println("[DELEÇÃO TERMINAL] Status: " + response.statusCode());
        System.out.println("[DELEÇÃO TERMINAL] Body: " + response.body());
    }

    public String consultarUsuarioNoTerminal(String userId) throws Exception {
        String url = "http://" + IP + "/cgi-bin/AccessUser.cgi?action=list&UserIDList[0]=" + userId;
        HttpResponse<String> response = DigestAuthUtil.sendWithDigestAuth(url, "GET", null, USERNAME, PASSWORD);
        System.out.println("[CONSULTA TERMINAL] Status: " + response.statusCode());
        System.out.println("[CONSULTA TERMINAL] Body:\n" + response.body());
        return response.body(); // retorna texto puro como na resposta da API
    }
}

