package br.com.wister.controller;

import br.com.wister.controller.dto.TopicoDTO;
import br.com.wister.controller.form.AtualizacaoTopicoForm;
import br.com.wister.controller.form.TopicoForm;
import br.com.wister.database.model.*;
import br.com.wister.database.repository.CursoRepository;
import br.com.wister.database.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "listaDeTopicos")  // vai guardar oretorno do metodo em cache ddepois da primeira requisição
                                          // ele guarda o retorno no cache entao se eu fizer outra requisicao do mesmo
                                          // metodoele nao vai novamente no banco ele vai apenas pegar da memoria
                                          // pra cada variacao de parametro ele faz uma requisição
    public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso,
                                 Pageable paginacao) {
        //posso deixar como padrao para retornar quando não hover filtros(é usado apenas quando nao se rcebe filtro)
        // @PageableDefault(sort = "id". direction = Direction.ASC), page =0, size = 10

        // vai receber por parametro um paginacao ai la na requisiçao vai mandar dessa forma: localhost:8080/topicos?page=0&size=10&sort=id,asc&sort=dataCriacao,desc
        // e deve ter na main a anotaçao @EnableSpringDataWebSupport
        Page<Topico> topicos;
        if (nomeCurso == null) {
            topicos = topicoRepository.findAll(paginacao);
        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
        }

        return TopicoDTO.converter(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable("id") Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)   // vai atualizar o cache que foi feito a requisição sendo usada apenas nos metodos que alteram registros emcache
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }


    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
