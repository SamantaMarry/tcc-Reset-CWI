package io.github.cwireset.tcc.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cwireset.tcc.domain.Endereco;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizarUsuarioRequest {

    @NotBlank(message = "Campo Obrigatório não informado")
    private String nome;


    @Email(message = "Campo Obrigatório não informado")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Campo Obrigatório não informado")
    private String senha;

    @NotNull(message = "Campo Obrigatório não informado")
    private LocalDate dataNascimento;

    private Endereco endereco;

}