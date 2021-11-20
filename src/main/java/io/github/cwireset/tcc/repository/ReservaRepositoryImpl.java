package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepositoryImpl extends PagingAndSortingRepository<Reserva, Long> {

    List<Reserva> findBySolicitanteId(Long idSolicitante);

    @Query("select r from Reserva r where r.anuncio.anunciante.id = ?1")
    List<Reserva> findByAnuncianteId(Long id);




    @Query("SELECT r FROM Reserva AS r " +
            "WHERE (" +
            "r.periodo.dataHoraInicial BETWEEN :dataChegada AND :dataSaida " +
            "OR r.periodo.dataHoraFinal BETWEEN :dataChegada AND :dataSaida " +
            "OR :dataChegada BETWEEN r.periodo.dataHoraInicial AND r.periodo.dataHoraFinal " +
            ") " +
            "AND r.anuncio.imovel.id = :imovelId " +
            "AND ( r.pagamento.status = io.github.cwireset.tcc.domain.StatusPagamento.PAGO " +
            "OR r.pagamento.status = io.github.cwireset.tcc.domain.StatusPagamento.PENDENTE )")
    List<Reserva> findAllValidSobreposicaodeDatas(@Param("imovelId") Long imovelId,
                                                  @Param("dataChegada") LocalDateTime dataChegada,
                                                  @Param("dataSaida") LocalDateTime dataSaida);
}