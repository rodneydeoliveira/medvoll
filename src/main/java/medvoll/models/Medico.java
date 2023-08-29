package medvoll.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.endereco.Endereco;
import medvoll.medico.DadosAtualizacaoMedico;
import medvoll.medico.DadosCadastroMedico;
import medvoll.enumeration.Especialidade;
import medvoll.medico.DadosRetornoMedico;
import org.springframework.web.bind.annotation.DeleteMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "medicos")
@Entity(name = "Medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.crm = dados.crm();
        this.email = dados.email();
        this.nome = dados.nome();
        this.especialidade = dados.especialidade();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());

    }
    public Medico(DadosRetornoMedico dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco()!= null){
            this.endereco.atualizarEndereco(dados.endereco());
        }
    }


    public void excluir() {
        this.ativo = false;
    }
    public void ativar(){
        this.ativo = true;
    }
}
