package io.github.cwireset.tcc.controller;


import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {


    private ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva resarvar(@RequestBody @Valid CadastrarReservaRequest cadastrarReservaRequest) throws Exception {
        return reservaService.salvar(cadastrarReservaRequest);
    }

    @GetMapping(path = "/solicitantes/{idSolicitante}")
    @ResponseStatus(HttpStatus.OK)
    public List<Reserva> consultarReservaSolicitante(@PathVariable
                                                    @Valid
                                                    @NotNull(message = "Campo obrigatório não informado. Favor informar" +
                                                            " o campo Id.") Long idSolicitante) throws Exception{
        return reservaService.consultarReservaSolicitante(idSolicitante);
    }

    @GetMapping(path = "/anuncios/anunciantes/{idAnunciante}")
    @ResponseStatus(HttpStatus.OK)
    public List<Reserva> consultarReservaAnunciante(@PathVariable
                                                     @Valid
                                                     @NotNull(message = "Campo obrigatório não informado. Favor informar" +
                                                             " o campo Id.") Long idAnunciante) throws Exception{
        return reservaService.consultarReservaAnunciante(idAnunciante);
    }

   /* @PutMapping("/{idReserva}/pagamentos")
    public void pagarReserva(@PathVariable
                                 @Valid
                                 @NotNull(message = "Campo obrigatório não informado. Favor informar" +
                                            " o campo Id.") Long idReserva, FormaPagamento formaPagamento)throws Exception{
        reservaService.pagarReserva(idReserva, formaPagamento);
    }*/
}
