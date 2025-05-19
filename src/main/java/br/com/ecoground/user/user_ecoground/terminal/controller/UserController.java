package br.com.ecoground.user.user_ecoground.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.ecoground.user.user_ecoground.common.exception.MatriculadoException;
import br.com.ecoground.user.user_ecoground.terminal.dto.MatriculadoDTO;
import br.com.ecoground.user.user_ecoground.terminal.service.MatriculadoService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MatriculadoService matriculadoService;

    @GetMapping("/{matricula}")
    public MatriculadoDTO showMatriculadoByMatricula(@PathVariable String matricula) throws MatriculadoException {
        return matriculadoService.getMatriculadoByMatricula(matricula);
    }
}

