package br.com.ecoground.user.user_ecoground.terminal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoground.user.user_ecoground.common.exception.MatriculadoException;
import br.com.ecoground.user.user_ecoground.terminal.dto.NovoUsuarioRequest;
import br.com.ecoground.user.user_ecoground.terminal.entity.Matriculado;
import br.com.ecoground.user.user_ecoground.terminal.model.UsuarioIntelbras;
import br.com.ecoground.user.user_ecoground.terminal.repository.MatriculadoRepository;
import br.com.ecoground.user.user_ecoground.terminal.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/admin")
public class TerminalController {

    private final UsuarioServiceImpl usuarioService;
    private final MatriculadoRepository matriculadoRepository;

    public TerminalController(UsuarioServiceImpl usuarioService, MatriculadoRepository matriculadoRepository) {
        this.usuarioService = usuarioService;
        this.matriculadoRepository = matriculadoRepository;
    }

    @GetMapping("/consultar-usuario/{matricula}")
    public ResponseEntity<?> consultarUsuarioNoTerminal(@PathVariable String matricula) {
        try {
            String dadosUsuario = usuarioService.consultarUsuarioNoTerminal(matricula);
            return ResponseEntity.ok(dadosUsuario);
        } catch (MatriculadoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao consultar usuário: " + e.getMessage());
        }
    }

    @PostMapping("/atualizar-usuario/{matricula}")
    public ResponseEntity<?> enviarUsuario(@PathVariable String matricula) throws Exception {
        try {
            Matriculado matriculado = matriculadoRepository.findByMatricula(matricula)
                    .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
            /*
             * if (matriculado == null) {
             * return
             * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Matriculado não encontrado"
             * );
             * }
             */
            UsuarioIntelbras usuario = new UsuarioIntelbras(
                    matriculado.getMatricula(),
                    matriculado.getPessoa().getNome(),
                    0, // userType
                    1, // authority
                    "234", // password - use alguma lógica real de senha
                    List.of(0), // doors
                    List.of(255), // timeSections
                    "2019-01-02 00:00:00", // validFrom
                    "2037-01-02 01:00:00" // validTo
            );
            usuarioService.enviarParaTerminal(usuario);
            return ResponseEntity.ok("Usuário enviado com sucesso!");
        } catch (MatriculadoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }

    @PostMapping("/cadastrar-usuario")
    public ResponseEntity<Void> cadastrarNovoUsuario(@RequestBody NovoUsuarioRequest request) throws Exception {
        try {
            usuarioService.cadastrarNovoUsuario(
                    request.getUserId(),
                    request.getNome(),
                    request.getSenha(),
                    request.getTipoUsuario(),
                    request.getAutoridade(),
                    request.getValidoDe(),
                    request.getValidoAte());
            return ResponseEntity.ok().build();
        } catch (MatriculadoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deletar-usuario/{matricula}")
    public ResponseEntity<Void> deletarUsuarioDoTerminal(@PathVariable String matricula) throws Exception {
        try {
            usuarioService.deletarDoTerminal(matricula);
            return ResponseEntity.ok().build();
        } catch (MatriculadoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
