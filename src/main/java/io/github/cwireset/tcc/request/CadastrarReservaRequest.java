package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.*;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarReservaRequest {

    private Long idSolicitante;
    private Long idAnuncio;
    private Periodo periodo;
    private Integer quantidadePessoas;

    public Reserva converterParaObjeto(CadastrarReservaRequest cadastrarReservaRequest, Anuncio anuncio,
                                       Usuario solicitante, Pagamento formaPagamento){
        return new Reserva(null,
                solicitante,
                anuncio,
                cadastrarReservaRequest.getPeriodo(),
                cadastrarReservaRequest.getQuantidadePessoas(),
                LocalDateTime.now(), formaPagamento);
    }














}
