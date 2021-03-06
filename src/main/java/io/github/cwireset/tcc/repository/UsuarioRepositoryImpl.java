package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositoryImpl extends PagingAndSortingRepository<Usuario, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByCpf(String cpf);

    @Query("select count(u.id) from Usuario u where u.email = ?1 and u.id <> ?2")
    int existsByEmailIdigual(String email, Long id);

}


