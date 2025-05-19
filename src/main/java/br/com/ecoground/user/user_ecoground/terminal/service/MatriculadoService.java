package br.com.ecoground.user.user_ecoground.terminal.service;

import br.com.ecoground.user.user_ecoground.terminal.dto.MatriculadoDTO;

public interface MatriculadoService {
    public MatriculadoDTO getMatriculadoByMatricula(String matricula);
}
