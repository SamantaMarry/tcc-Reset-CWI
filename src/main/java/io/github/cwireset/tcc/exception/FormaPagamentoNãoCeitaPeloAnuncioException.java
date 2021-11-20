package io.github.cwireset.tcc.exception;

import io.github.cwireset.tcc.domain.FormaPagamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FormaPagamentoNãoCeitaPeloAnuncioException extends RuntimeException {
    public FormaPagamentoNãoCeitaPeloAnuncioException(FormaPagamento formaEscolhida, List<FormaPagamento> formasAceitas) {
        super(String.format("O anúncio não aceita %s como forma de pagamento. As formas " +
                "aceitas são %s.",formaEscolhida, formasAceitas));
    }
}
