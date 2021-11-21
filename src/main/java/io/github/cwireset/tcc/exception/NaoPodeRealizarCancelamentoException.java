package io.github.cwireset.tcc.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoPodeRealizarCancelamentoException extends RuntimeException{
    public NaoPodeRealizarCancelamentoException(){
        super("Não é possível realizar o cancelamento para esta reserva, pois ela não está no status PENDENTE.");
    }
}
