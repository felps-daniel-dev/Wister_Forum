package br.com.wister.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroDeFormularioDTO {

    private String campo;
    private String erro;

}
