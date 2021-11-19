package io.github.cwireset.tcc.repository;


import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.StatusAnuncio;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImovelRepositoryImpl extends PagingAndSortingRepository<Imovel, Long> {


    List<Imovel> findByProprietarioId(Long Id);


    @Query("select (count(a) > 0) from Anuncio a where a.imovel.id = ?1 and a.status = 'ativo'")
    boolean existsByAnuncio(Long id, StatusAnuncio ativo);
}
