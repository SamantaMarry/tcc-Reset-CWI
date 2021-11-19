package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepositoryImpl extends PagingAndSortingRepository<Reserva, Long> {

    List<Reserva> findBySolicitanteId(Long idSolicitante);

    @Query("select r from Reserva r where r.anuncio.anunciante.id = ?1")
    List<Reserva> findByAnuncianteId(Long id);


}