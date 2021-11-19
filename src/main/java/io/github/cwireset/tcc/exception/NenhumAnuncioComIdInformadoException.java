package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NenhumAnuncioComIdInformadoException extends RuntimeException{
    public NenhumAnuncioComIdInformadoException(Long id){
            super(String.format("Nenhum(a) Anuncio com Id com o valor '%s' foi encontrado", id));

        }
}
