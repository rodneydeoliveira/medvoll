package medvoll.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        String numero,
        @NotBlank
        String bairro,
        String complemento,
        @NotBlank
        String cidade,
        @NotBlank
        @Pattern(regexp = "\\D{2}")
        String uf,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep) {
}
