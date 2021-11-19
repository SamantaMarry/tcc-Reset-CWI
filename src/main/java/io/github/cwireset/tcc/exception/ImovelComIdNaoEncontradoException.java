package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImovelComIdNaoEncontradoException extends RuntimeException{
    public ImovelComIdNaoEncontradoException(Long id) {
        super(String.format("Nenhum(a) Imovel com Id com o valor '%s' foi encontrado.",id));
    }
}
