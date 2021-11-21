package io.github.cwireset.tcc.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Campo Obrigatório não informado")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve ser informado no formato 99999-999.")
    private String cep;

    @NotBlank(message = "Campo Obrigatório não informado")
    private String logradouro;

    @NotBlank(message = "Campo Obrigatório não informado")
    private String numero;

    @NotBlank(message = "Campo Obrigatório não informado")
    private String complemento;

    @NotBlank(message = "Campo Obrigatório não informado")
    private String bairro;

    @NotBlank(message = "Campo Obrigatório não informado")
    private String cidade;

    @NotBlank(message = "Campo Obrigatório não informado")
    private String estado;

}
