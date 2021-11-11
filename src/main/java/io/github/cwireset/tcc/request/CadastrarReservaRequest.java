package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Periodo;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.domain.Usuario;
import lombok.*;

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

    public Reserva converterParaObjeto(CadastrarReservaRequest cadastrarReservaRequest, Usuario solicitante,
                                       Anuncio anuncio){
        return new Reserva(null,
                solicitante,
                anuncio,
                cadastrarReservaRequest.getPeriodo(),
                cadastrarReservaRequest.getQuantidadePessoas());
    }

}
