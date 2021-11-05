package io.github.cwireset.tcc.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cwireset.tcc.domain.Endereco;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizarUsuarioRequest {

    @NotNull(message = "Campo Obrigatório não informado")
    private String nome;


    @Email(message = "Campo Obrigatório não informado")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Campo Obrigatório não informado")
    private String senha;

    @NotNull(message = "Campo Obrigatório não informado")
    private LocalDate dataNascimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

}
