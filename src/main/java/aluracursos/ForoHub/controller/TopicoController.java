package aluracursos.ForoHub.controller;


import aluracursos.ForoHub.domain.topico.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@ResponseBody
@RequestMapping("/topicos")

public class TopicoController {

    @Autowired
    private TopicoService service;
    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> agregarTopic(@RequestBody @Valid DatosTopico datos, HttpServletRequest request, UriComponentsBuilder uriBuilder){
        var response = service.agregarTopico(datos,request);
        var res = new RespuestaTopico(response);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(url).body(res);

    }
    @GetMapping
    public ResponseEntity<Page<RespuestaTopico>> mostrarTopicos(Pageable page){
        return ResponseEntity.ok(repository.findAll(page).map(RespuestaTopico::new));
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Optional<Topico>> mostrarTopicoPorId(@PathVariable Long id){
        Optional<Topico> topico = repository.findById(id);

        if(topico.isPresent()){
            return ResponseEntity.ok(topico);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetallesTopico> actualizarTopico(@PathVariable Long id, @Valid @RequestBody ActualizarTopico datos, HttpServletRequest request){
        var response = service.actualizar(id,datos,request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity<Void> borrarTopico(@PathVariable Long id, HttpServletRequest request){
        service.borrar(id, request);
        return ResponseEntity.noContent().build();
    }


}
