package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataFinalDaReservaMiorQueDataInicialException extends RuntimeException {
    public DataFinalDaReservaMiorQueDataInicialException(){
        super("Período inválido! A data final da reserva precisa ser maior do que a data inicial.");
    }
}
