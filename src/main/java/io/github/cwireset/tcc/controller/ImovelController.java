package io.github.cwireset.tcc.controller;


import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import io.github.cwireset.tcc.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {


    private ImovelService imovelService;

    @Autowired
    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Imovel cadastrarImovel(@RequestBody @Valid CadastrarImovelRequest cadastrarImovelRequest) throws Exception {
        return imovelService.cadastrarImovel(cadastrarImovelRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Imovel> listarImoveis(@Valid Pageable pageable){
        return imovelService.listarImoveis(pageable);
    }

   /* @GetMapping("/proprietarios/{idProprietario}")
    @ResponseStatus(HttpStatus.OK)
    public Imovel consultarImovelProprietario(@Valid Long idProprietario){
        return imovelService.consultarImovelProprietario(idProprietario);
    }*/

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Imovel consultarImovelId(@PathVariable
                                        @Valid
                                        @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Id.") Long id) throws Exception {
        return imovelService.consultarImovelId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removerImovel(@PathVariable
                                    @Valid
                                    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Id.") Long id) throws Exception{
        this.imovelService.removerImovel(id);

    }




}
