package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.*;
import lombok.*;
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
    private Long idImovel;

    @NotNull(message = "Campo Obrigatório não informado")
    private Long idAnunciante;

    @NotNull(message = "Campo Obrigatório não informado")
    private TipoAnuncio tipoAnuncio;

    @NotNull(message = "Campo Obrigatório não informado")
    private BigDecimal valorDiaria;

    @NotNull(message = "Campo Obrigatório não informado")
    private List<FormaPagamento> formasAceitas;

    @NotNull(message = "Campo Obrigatório não informado")
    private String descricao;

    public Anuncio converterParaObjeto(CadastrarAnuncioRequest cadastrarAnuncioRequest, Imovel imovelAnunciando,
                                       Usuario anunciante){
        return new Anuncio(null,
                cadastrarAnuncioRequest.getTipoAnuncio(),
                imovelAnunciando,
                anunciante,
                cadastrarAnuncioRequest.getValorDiaria(),
                cadastrarAnuncioRequest.getFormasAceitas(),
                cadastrarAnuncioRequest.getDescricao(),StatusAnuncio.ATIVO);
    }
}