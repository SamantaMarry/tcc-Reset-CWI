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
    private Usuario proprietario;

    private List<CaracteristicaImovel> caracteristicas;


   public Imovel converterParaObjeto(CadastrarImovelRequest cadastrarImovelRequest, Usuario usuario) {return new
           Imovel(cadastrarImovelRequest.getIdentificacao(), cadastrarImovelRequest.getEndereco(),
           cadastrarImovelRequest.getProprietario(),usuario,
           cadastrarImovelRequest.getCaracteristicas());}

}

