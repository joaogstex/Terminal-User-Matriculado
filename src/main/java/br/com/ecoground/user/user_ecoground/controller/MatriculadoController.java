package br.com.ecoground.user.user_ecoground.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.ecoground.user.user_ecoground.dto.MatriculadoDTO;
import br.com.ecoground.user.user_ecoground.service.MatriculadoService;

@RestController
@RequestMapping("/api/matriculados")
public class MatriculadoController {

    @Autowired
    private MatriculadoService matriculadoService;

    @GetMapping("/{matricula}")
    public MatriculadoDTO showMatriculadoByMatricula(@PathVariable String matricula) {
        return matriculadoService.getMatriculadoByMatricula(matricula);
    }
}

