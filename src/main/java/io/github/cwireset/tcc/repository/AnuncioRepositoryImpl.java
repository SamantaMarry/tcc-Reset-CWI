package io.github.cwireset.tcc.repository;


import io.github.cwireset.tcc.domain.Anuncio;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepositoryImpl extends PagingAndSortingRepository<Anuncio, Long> {

}
