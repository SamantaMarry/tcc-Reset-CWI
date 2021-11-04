package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepositoryImpl extends PagingAndSortingRepository<Usuario, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<Usuario> findById(Long id);
}
