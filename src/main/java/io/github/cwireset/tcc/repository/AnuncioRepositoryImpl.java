package io.github.cwireset.tcc.repository;


import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.StatusAnuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepositoryImpl extends PagingAndSortingRepository<Anuncio, Long> {

    List<Anuncio> findByAnuncianteIdAndStatus(Long Id, StatusAnuncio ativo);


    Page<Anuncio> findByStatus(StatusAnuncio ativo, Pageable pageable);

    @Query("select (count(a) > 0) from Anuncio a where a.imovel.id = ?1 and a.status = 'ativo'")
    boolean existsByStatusAtivo(Long id, StatusAnuncio ativo);







}
