package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.CpfJaCadastradoException;
import io.github.cwireset.tcc.exception.EmailJaCadastradoException;
import io.github.cwireset.tcc.exception.IdNaoEncontradoException;
import io.github.cwireset.tcc.repository.UsuarioRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositoryImpl repository;


    public Usuario cadastrarUsuario(Usuario usuario) throws Exception{

        if (repository.existsByEmail(usuario.getEmail())){
            throw new EmailJaCadastradoException(usuario.getEmail());
        }

        if (repository.existsByCpf(usuario.getCpf())){
            throw new CpfJaCadastradoException(usuario.getCpf());
        }

        return repository.save(usuario);


    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Usuario> consultarUsuario(Long id) throws Exception{
        if (!repository.existsById(id)){
            throw new IdNaoEncontradoException(id);

        }
        return repository.findById(id);
    }
}
