package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid PacienteCadastrarDTO data, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(data);
        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhadosPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteReponseDTO>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginate){
        var page = repository.findAll(paginate).map(PacienteReponseDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarPaciente(@RequestBody @Valid PacienteEditarDTO data){
        var paciente = repository.getReferenceById(data.id());
        paciente.editarInformacoes(data);

        return ResponseEntity.ok(new DadosEditarDetalhadosPacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPaciente(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhadosPacienteDTO(paciente));
    }
}
