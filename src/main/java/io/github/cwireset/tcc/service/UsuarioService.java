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

    public Usuario consultarUsuarioId(Long id) throws Exception{
        return repository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException(id));

    }

    public Usuario consultarUsuarioCpf(String cpf) throws Exception {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new CpfNaoEncontradoException(cpf));
    }


    public Usuario alterarUsuario(Long id, AtualizarUsuarioRequest atualizarUsuarioRequest) throws Exception {
        Usuario usuario = consultarUsuarioId(id);

        if (!repository.existsById(id)){
            throw new IdNaoEncontradoException(id);
        }
        if (repository.existsByEmail(atualizarUsuarioRequest.getEmail())){
            throw new EmailJaCadastradoException(atualizarUsuarioRequest.getEmail());
        }
        usuario.setNome(atualizarUsuarioRequest.getNome());
        usuario.setEmail(atualizarUsuarioRequest.getEmail());
        usuario.setSenha(atualizarUsuarioRequest.getSenha());
        usuario.setEndereco(atualizarUsuarioRequest.getEndereco());
        return repository.save(usuario);
    }


}
