package medvoll.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.endereco.DadosEndereco;
import medvoll.endereco.Endereco;
import medvoll.paciente.DadosAtualizacaoPaciente;
import medvoll.paciente.DadosCadastroPaciente;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "pacientes")
@Entity(name= "Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome()!= null){
            this.nome = dados.nome();
        }
        if (dados.telefone()!= null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco()!= null){
            this.endereco.atualizarEndereco(dados.endereco());
        }

        }
    public void excluir(){
        this.ativo = false;
    }
    public void ativar(){
        this.ativo = true;
    }
}
