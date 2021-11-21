package io.github.cwireset.tcc.exception;

import io.github.cwireset.tcc.domain.Imovel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoPodeExcluirImovelComAnuncioException extends RuntimeException{
        public NaoPodeExcluirImovelComAnuncioException(Long id){
            super("Não é possível excluir um imóvel que possua um anúncio.");
        }
}
