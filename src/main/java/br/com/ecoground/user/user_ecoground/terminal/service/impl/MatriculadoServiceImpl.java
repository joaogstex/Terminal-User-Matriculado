package br.com.ecoground.user.user_ecoground.terminal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecoground.user.user_ecoground.terminal.dto.MatriculadoDTO;
import br.com.ecoground.user.user_ecoground.terminal.entity.Matriculado;
import br.com.ecoground.user.user_ecoground.terminal.repository.MatriculadoRepository;
import br.com.ecoground.user.user_ecoground.terminal.service.MatriculadoService;

@Service
public class MatriculadoServiceImpl implements MatriculadoService {

    @Autowired
    private MatriculadoRepository matriculadoRepository;

    public MatriculadoDTO getMatriculadoByMatricula(String matricula) {
        Matriculado matriculado = matriculadoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        return MatriculadoDTO.fromEntity(matriculado);
    }
}