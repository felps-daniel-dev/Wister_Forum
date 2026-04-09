package br.com.wister.controller.dto;

import br.com.wister.database.model.Resposta;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RespostaDTO {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDTO(Resposta resposta){
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }
}
