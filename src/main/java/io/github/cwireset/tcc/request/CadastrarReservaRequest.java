package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarReservaRequest {

    @NotNull(message = "Campo Obrigatório não informado")
    private Long idSolicitante;
    @NotNull(message = "Campo Obrigatório não informado")
    private Long idAnuncio;
    @NotNull(message = "Campo Obrigatório não informado")
    private Periodo periodo;
    @NotNull(message = "Campo Obrigatório não informado")
    private Integer quantidadePessoas;

    public Reserva converterParaObjeto(CadastrarReservaRequest cadastrarReservaRequest, Anuncio anuncio,
                                       Usuario solicitante, Pagamento pagamento){
        return new Reserva(null,
                solicitante,
                anuncio,
                cadastrarReservaRequest.getPeriodo(),
                cadastrarReservaRequest.getQuantidadePessoas(),
                LocalDateTime.now(), pagamento);
    }
}
