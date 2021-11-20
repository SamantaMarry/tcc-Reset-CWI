package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdReservaNãoLocalizadaException extends RuntimeException {
    public IdReservaNãoLocalizadaException(Long idReserva) {
        super(String.format("Nenhum(a) Reserva com Id com o valor '%s' foi encontrado.", idReserva));
    }
}
