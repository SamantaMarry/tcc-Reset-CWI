package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.CpfJaCadastradoException;
import io.github.cwireset.tcc.exception.CpfNaoEncontradoException;
import io.github.cwireset.tcc.exception.EmailJaCadastradoException;
import io.github.cwireset.tcc.exception.IdNaoEncontradoException;
import io.github.cwireset.tcc.repository.UsuarioRepositoryImpl;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
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

    public Optional<Usuario> consultarUsuarioId(Long id) throws Exception{
        if (!repository.existsById(id)){
            throw new IdNaoEncontradoException(id);

        }
        return repository.findById(id);
    }

    public Optional<Usuario> consultarUsuarioCpf(String cpf) throws Exception{
        if (!repository.existsByCpf(cpf)){
            throw new CpfNaoEncontradoException(cpf);

        }
        return repository.findByCpf(cpf);
    }

    public Usuario alterarUsuario(Long id, AtualizarUsuarioRequest atualizarUsuarioRequest) throws Exception {
        Optional<Usuario> usuario = consultarUsuarioId(id);

        if (!repository.existsById(id)){
            throw new IdNaoEncontradoException(id);
        }
        if (repository.existsByEmail(atualizarUsuarioRequest.getEmail())){
            throw new EmailJaCadastradoException(atualizarUsuarioRequest.getEmail());
        }
        usuario.get().setNome(atualizarUsuarioRequest.getNome());
        usuario.get().setEmail(atualizarUsuarioRequest.getEmail());
        usuario.get().setSenha(atualizarUsuarioRequest.getSenha());
        usuario.get().setEndereco(atualizarUsuarioRequest.getEndereco());
        return repository.save(usuario.get());
    }
}
