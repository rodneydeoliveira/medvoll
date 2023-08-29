package medvoll.medico;

import medvoll.enumeration.Especialidade;
import medvoll.models.Medico;

public record DadosRetornoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DadosRetornoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
