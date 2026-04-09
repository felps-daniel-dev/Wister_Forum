package br.com.wister.controller.form;

import br.com.wister.database.model.Curso;
import br.com.wister.database.model.Topico;
import br.com.wister.database.repository.CursoRepository;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class TopicoForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;

    public Topico converter(CursoRepository repository) {

        Curso curso = repository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
