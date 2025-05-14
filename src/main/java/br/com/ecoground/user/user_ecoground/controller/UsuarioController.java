package br.com.ecoground.user.user_ecoground.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoground.user.user_ecoground.dto.NovoUsuarioRequest;
import br.com.ecoground.user.user_ecoground.entity.Matriculado;
import br.com.ecoground.user.user_ecoground.model.UsuarioIntelbras;
import br.com.ecoground.user.user_ecoground.repository.MatriculadoRepository;
import br.com.ecoground.user.user_ecoground.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final MatriculadoRepository matriculadoRepository;

    public UsuarioController(UsuarioService usuarioService, MatriculadoRepository matriculadoRepository) {
        this.usuarioService = usuarioService;
        this.matriculadoRepository = matriculadoRepository;
    }

    @PostMapping("/enviar/{matricula}")
    public ResponseEntity<?> enviarUsuario(@PathVariable String matricula) {
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }

    @PostMapping("/cadastrar-novo")
    public ResponseEntity<Void> cadastrarNovoUsuario(@RequestBody NovoUsuarioRequest request) {
        try {
            usuarioService.cadastrarNovoUsuario(
                    request.getUserId(),
                    request.getNome(),
                    request.getSenha(),
                    request.getTipoUsuario(),
                    request.getAutoridade(),
                    request.getValidoDe(),
                    request.getValidoAte()
                    );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
