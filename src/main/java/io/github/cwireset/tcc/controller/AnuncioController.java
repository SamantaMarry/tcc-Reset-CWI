package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Anuncio;

import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import io.github.cwireset.tcc.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/anuncios")
@RestController
public class AnuncioController {

     private AnuncioService anuncioService;

    @Autowired
     public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio anunciarImovel(@RequestBody @Valid CadastrarAnuncioRequest cadastrarAnuncioRequest) throws Exception{
        return this.anuncioService.anunciarImovel(cadastrarAnuncioRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Anuncio> listarAnuncios(@Valid Pageable pageable){
        return anuncioService.listarAnuncios(pageable);
    }

    @GetMapping(path = "/anunciantes/{idAnunciante}")
    @ResponseStatus(HttpStatus.OK)
    public List<Anuncio> consultarAnuncioId(@PathVariable @Valid Long idAnunciante) throws Exception{
        return anuncioService.consultarAnuncioIdAnunciante(idAnunciante);
    }

    @DeleteMapping(path = "/{idAnuncio}")
    @ResponseStatus(HttpStatus.OK)
    public void removerAnuncio(@PathVariable
                              @Valid
                              @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Id.") Long idAnuncio) throws Exception{
        this.anuncioService.removerAnuncio(idAnuncio);

    }
}