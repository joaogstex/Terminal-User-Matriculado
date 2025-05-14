package br.com.ecoground.user.user_ecoground.dto;

import br.com.ecoground.user.user_ecoground.entity.Matriculado;
import lombok.Data;

@Data
public class MatriculadoDTO {
    private String matricula;
    private String funcao;
    private Boolean ativo;
    private String pessoaNome;
    private String pessoaEmail;

    public static MatriculadoDTO fromEntity(Matriculado m) {
        MatriculadoDTO dto = new MatriculadoDTO();
        dto.matricula = m.getMatricula();
        dto.funcao = m.getFuncao();
        dto.ativo = m.getAtivo();
        dto.pessoaNome = m.getPessoa() != null ? m.getPessoa().getNome() : null;
        dto.pessoaEmail = m.getPessoa() != null ? m.getPessoa().getEmail() : null;
        return dto;
    }
}
