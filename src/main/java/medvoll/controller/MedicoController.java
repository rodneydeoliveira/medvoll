package medvoll.controller;

import jakarta.validation.Valid;
import medvoll.medico.DadosAtualizacaoMedico;
import medvoll.medico.DadosCadastroMedico;
import medvoll.medico.DadosDetalhamentoMedico;
import medvoll.medico.DadosRetornoMedico;
import medvoll.models.Medico;
import medvoll.models.Paciente;
import medvoll.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrarMedico(@Valid @RequestBody DadosCadastroMedico dados)
    {
        repository.save(new Medico(dados));
    }
    @GetMapping("/{id}")
    public Medico obterMedico(@PathVariable("id")Long id){
       return repository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Medico não encontrado!");

});
    }
    @GetMapping
    public ResponseEntity<Page<DadosRetornoMedico>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable dado){
    var page = repository.findAllByAtivoTrue(dado).map(DadosRetornoMedico::new);

    return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizarRegistro(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity reativarRegistro(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.ativar();
        return ResponseEntity.ok("Registro Ativado!");
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirRegistro(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }
}
