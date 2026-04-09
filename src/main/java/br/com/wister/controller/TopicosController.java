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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> lista(String filtro) {
        List<Topico> topicos;
        if (filtro == null) topicos = topicoRepository.findAll();
        else topicos = topicoRepository.findByCursoNome(filtro);

        return TopicoDTO.converter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public DetalhesTopicoDTO detalhar(@PathVariable("id") Long id){
        Topico topico = topicoRepository.getOne(id);
        return new DetalhesTopicoDTO(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
        Topico topico = form.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDTO(topico));
    }
}
