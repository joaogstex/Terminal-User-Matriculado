package br.com.ecoground.user.user_ecoground.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "PESSOA", schema = "FORACESSO")
public class Pessoa {

    @Id
    @Column(name = "PESS_ID")
    private Long id;

    @Column(name = "PESS_NOME")
    private String nome;

    @Column(name = "PESS_NOMEABREVIADO")
    private String nomeAbreviado;

    @Column(name = "PESS_NOMEFONETICO")
    private String nomeFonetico;

    @Column(name = "PESS_IDIOMA")
    private String idioma;

    @Column(name = "PESS_DOCUMENTO")
    private String documento;

    @Column(name = "PESS_ORGAOEMISSOR")
    private String orgaoEmissor;

    @Column(name = "TIDO_ID")
    private Long tipoDocumentoId;

    @Column(name = "PESS_DOCUMENTOUNICO")
    private String documentoUnico;

    @Column(name = "PESS_EMAIL")
    private String email;

    @Column(name = "MTBQ_ID")
    private Long mtbqId;

    @Column(name = "PESS_ACAORECEPCAO")
    private String acaoRecepcao;

    @Column(name = "PESS_DATAMODIFICACAO")
    private LocalDateTime dataModificacao;

    @Column(name = "MATR_IDUSUARIO")
    private Long matriculaUsuarioId;
}

