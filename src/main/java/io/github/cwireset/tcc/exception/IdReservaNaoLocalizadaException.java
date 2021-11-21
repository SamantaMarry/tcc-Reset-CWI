package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdReservaNaoLocalizadaException extends RuntimeException {
    public IdReservaNaoLocalizadaException(Long idReserva) {
        super(String.format("Nenhum(a) Reserva com Id com o valor '%s' foi encontrado.", idReserva));
    }
}
