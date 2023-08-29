package medvoll.controller;

import jakarta.validation.Valid;
import medvoll.models.Paciente;
import medvoll.paciente.DadosAtualizacaoPaciente;
import medvoll.paciente.DadosCadastroPaciente;
import medvoll.paciente.DadosRetornoPaciente;
import medvoll.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@Valid @RequestBody DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosRetornoPaciente> listarPacientesd(@PageableDefault(size = 10, sort = {"nome"}) Pageable dados) {
        return repository.findAllByAtivoTrue(dados).map(DadosRetornoPaciente::new);
    }

    @GetMapping("/{id}")
    public Paciente obterPaciente(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Paciente não encontrado");
        });
    }

    @PutMapping
    @Transactional
    public void atualizarRegistro(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public void excluirRegistro(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }


}



