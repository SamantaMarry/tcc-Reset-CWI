package io.github.cwireset.tcc.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Campo Obrigatório não informado")
    private String nome;


    @Email(message = "Campo Obrigatório não informado")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Campo Obrigatório não informado")
    private String senha;

    @Pattern(regexp = "\\d{11}", message = "O CPF deve ser informado no formato 99999999999.")
    @NotBlank(message = "Campo Obrigatório não informado")
    private String cpf;


    @NotNull(message = "Campo Obrigatório não informado")
    private LocalDate dataNascimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

}
