package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImovelComAnuncioException extends RuntimeException{
    public ImovelComAnuncioException(Long id) {
        super(String.format("Já existe um recurso do tipo Anuncio com IdImovel com o valor '%s'.",id));
    }

}
