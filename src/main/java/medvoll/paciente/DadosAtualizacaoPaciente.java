package medvoll.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import medvoll.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,

        DadosEndereco endereco
) {
}
