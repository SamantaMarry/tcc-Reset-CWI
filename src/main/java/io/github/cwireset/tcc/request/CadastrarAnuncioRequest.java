package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.TipoAnuncio;
import io.github.cwireset.tcc.domain.Usuario;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarAnuncioRequest {

    @NotNull(message = "Campo Obrigatório não informado")
    private TipoAnuncio tipoAnuncio;

    @NotNull(message = "Campo Obrigatório não informado")
    private Imovel imovel;

    @NotNull(message = "Campo Obrigatório não informado")
    private Usuario anunciante;

    @NotNull(message = "Campo Obrigatório não informado")
    private BigDecimal valorDiaria;

    @NotNull(message = "Campo Obrigatório não informado")
    private List<FormaPagamento> formasAceitas;

    @NotBlank(message = "Campo Obrigatório não informado")
    private String descricao;

}
