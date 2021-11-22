package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.*;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarImovelRequest {

    @NotNull(message = "Campo Obrigatório não informado")
    private TipoImovel tipoImovel;

    @NotNull(message = "Campo Obrigatório não informado")
    private Endereco endereco;

    private String identificacao;

    @NotNull(message = "Campo Obrigatório não informado")
    private Long idProprietario;

    private List<CaracteristicaImovel> caracteristicas;


   public Imovel converterParaObjeto(CadastrarImovelRequest cadastrarImovelRequest, Usuario proprietario) {return new
           Imovel(null,
           cadastrarImovelRequest.getIdentificacao(),
           cadastrarImovelRequest.getTipoImovel(),
           cadastrarImovelRequest.getEndereco(),
           proprietario,
           cadastrarImovelRequest.getCaracteristicas());
   }
}

