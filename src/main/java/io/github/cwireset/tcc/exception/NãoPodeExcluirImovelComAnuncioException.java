package io.github.cwireset.tcc.exception;

import io.github.cwireset.tcc.domain.Imovel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NãoPodeExcluirImovelComAnuncioException extends RuntimeException{
        public NãoPodeExcluirImovelComAnuncioException(Long id){
            super("Não é possível excluir um imóvel que possua um anúncio.");
        }
}
