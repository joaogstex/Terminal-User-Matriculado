package br.com.ecoground.user.user_ecoground.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "MATRICULADO", schema = "FORACESSO")
public class Matriculado {

    @Id
    @Column(name = "MATR_ID")
    private Long id;

    @Column(name = "EMPR_GRUPO")
    private String grupoEmpresa;

    @Column(name = "MATR_MATRICULA")
    private String matricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PESS_ID")
    private Pessoa pessoa;

    @Column(name = "MATR_TELEFONE")
    private String telefone;

    @Column(name = "MATR_RAMALPRINCIPAL")
    private String ramalPrincipal;

    @Column(name = "MATR_RAMALSECUNDARIO")
    private String ramalSecundario;

    @Column(name = "MATR_FUNCAO")
    private String funcao;

    @Column(name = "MATR_ATIVO")
    private Boolean ativo;

    @Column(name = "MATR_MARCACOESDISPONIVEIS")
    private Integer marcacoesDisponiveis;

    @Column(name = "MATR_SENHA")
    private String senha;

    @Column(name = "MATR_SENHAPANICO")
    private String senhaPanico;

    @Column(name = "MATR_USAHORARIOSPERFILACESSO")
    private Boolean usaHorariosPerfilAcesso;

    @Column(name = "CNTR_ID")
    private Long contratoId;

    @Column(name = "MATR_ENVIADADOSREP")
    private Boolean enviaDadosREP;

    @Column(name = "MATR_PIS")
    private String pis;

    @Column(name = "MATR_VERIFICABIOMETRIAREP")
    private Boolean verificaBiometriaREP;

    @Column(name = "MATR_ENVIAIDTFTECLADOREP")
    private Boolean enviaIdTecladoREP;

    @Column(name = "MATR_SENHAREP")
    private String senhaREP;

    @Column(name = "MATR_ENVIATBS")
    private Boolean enviaTBS;

    @Column(name = "MATR_DATAINATIVO")
    private LocalDate dataInativo;
}
