package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
import io.github.cwireset.tcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarUsuario(@RequestBody @Valid Usuario usuario) throws Exception{
        return usuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Usuario> listarUsuarios(@Valid Pageable pageable){
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping(path = "/{idUsuario}")
    public Usuario consultarUsuarioId(@PathVariable
                                                @Valid
                                                @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Id.")
                                                         Long idUsuario) throws Exception {
        return usuarioService.consultarUsuarioId(idUsuario);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public Usuario consultarUsuarioCpf(@PathVariable
                                                 @Valid
                                                 @NotNull(message = "Campo obrigatório não informado. Favor informar o campo CPF.")
                                                          String cpf) throws Exception {
        return usuarioService.consultarUsuarioCpf(cpf);
    }

    @PutMapping(path = "/{id}")
    public void alterarUsuario(@PathVariable
                               @Valid
                               @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Id.")
                                        Long id, @RequestBody @Valid AtualizarUsuarioRequest atualizarUsuarioRequest) throws Exception{
        usuarioService.alterarUsuario(id, atualizarUsuarioRequest);
    }
}
