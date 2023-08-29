package medvoll.paciente;

import medvoll.models.Paciente;

public record DadosRetornoPaciente(Long id, String nome,
                                   String email,
                                   String cpf) {
    public DadosRetornoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
