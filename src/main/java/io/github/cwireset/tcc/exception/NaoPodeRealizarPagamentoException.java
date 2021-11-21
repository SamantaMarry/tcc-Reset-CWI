package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoPodeRealizarPagamentoException extends RuntimeException {
    public NaoPodeRealizarPagamentoException(){
        super("Não é possível realizar o pagamento para esta reserva, pois ela não está no status PENDENTE.");
    }
}
