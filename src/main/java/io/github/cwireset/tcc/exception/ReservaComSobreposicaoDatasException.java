package io.github.cwireset.tcc.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservaComSobreposicaoDatasException extends RuntimeException {
    public ReservaComSobreposicaoDatasException(){
        super("Este anuncio já esta reservado para o período informado.");

    }
}
