package io.github.cwireset.tcc.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo Obrigatório não informado")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve ser informado no formato 99999-999.")
    private String cep;

    @NotNull(message = "Campo Obrigatório não informado")
    private String logradouro;

    @NotNull(message = "Campo Obrigatório não informado")
    private String numero;

    @NotNull(message = "Campo Obrigatório não informado")
    private String complemento;

    @NotNull(message = "Campo Obrigatório não informado")
    private String bairro;

    @NotNull(message = "Campo Obrigatório não informado")
    private String cidade;

    @NotNull(message = "Campo Obrigatório não informado")
    private String estado;

}
