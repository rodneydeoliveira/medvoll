package medvoll.medico;

import jakarta.validation.constraints.NotNull;
import medvoll.endereco.DadosEndereco;

public record DadosAtualizacaoMedico (
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco

){

}
