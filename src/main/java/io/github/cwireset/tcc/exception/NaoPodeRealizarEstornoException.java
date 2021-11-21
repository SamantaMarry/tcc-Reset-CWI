package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoPodeRealizarEstornoException extends RuntimeException {
    public NaoPodeRealizarEstornoException(){
        super("Não é possível realizar o estorno para esta reserva, pois ela não está no status PAGO.");
    }
}
