package io.github.cwireset.tcc.exception;

import io.github.cwireset.tcc.domain.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JaExisteAnuncioComIdImovelException extends RuntimeException{
    public JaExisteAnuncioComIdImovelException(@NotNull(message = "Campo Obrigatório não informado") Usuario id){
        super(String.format("Já existe um recurso do tipo Anuncio com IdImovel com o valor '%s'.", id));

    }
}
