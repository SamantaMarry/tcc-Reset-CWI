package io.github.cwireset.tcc.controller;


import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva resarvar(@RequestBody @Valid CadastrarReservaRequest cadastrarReservaRequest) throws Exception {
        return reservaService.reservar(cadastrarReservaRequest);
    }
}
