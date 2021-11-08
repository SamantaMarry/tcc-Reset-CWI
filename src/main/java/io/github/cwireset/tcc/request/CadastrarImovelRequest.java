package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarImovelRequest {

    @NotNull(message = "Campo Obrigatório não informado")
    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;


    @NotNull(message = "Campo Obrigatório não informado")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @NotNull(message = "Campo Obrigatório não informado")
    private String identificacao;

    @NotNull(message = "Campo Obrigatório não informado")
    @ManyToOne
    @JoinColumn(name = "id_proprietario")
    private Usuario proprietario;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_imovel")
    private List<CaracteristicaImovel> caracteristicas;


   public Imovel converterParaObjeto(CadastrarImovelRequest cadastrarImovelRequest, Usuario usuario) {return new
           Imovel(cadastrarImovelRequest.getIdentificacao(), cadastrarImovelRequest.getEndereco(),
           cadastrarImovelRequest.getProprietario(),usuario,
           cadastrarImovelRequest.getCaracteristicas());}

}

