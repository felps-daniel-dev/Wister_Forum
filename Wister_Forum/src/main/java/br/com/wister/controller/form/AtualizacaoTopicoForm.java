package br.com.wister.controller.form;

import br.com.wister.database.model.Topico;
import br.com.wister.database.repository.TopicoRepository;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class AtualizacaoTopicoForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {

        Topico topico = topicoRepository.getOne(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        return topico;
    }
}
