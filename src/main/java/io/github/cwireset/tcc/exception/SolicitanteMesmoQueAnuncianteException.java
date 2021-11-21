package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SolicitanteMesmoQueAnuncianteException extends RuntimeException {
    public SolicitanteMesmoQueAnuncianteException(){
            super("O solicitante de uma reserva não pode ser o próprio anunciante.");
    }
}
