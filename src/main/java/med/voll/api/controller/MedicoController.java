package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedicos(@RequestBody @Valid MedicoCadastrarDTO data, UriComponentsBuilder uriBuilder){
        var medico = new Medico(data);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhadosMedicoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponseDTO>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginate){
        var page = repository.findAll(paginate).map(MedicoResponseDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarMedico(@RequestBody @Valid MedicoEditarDTO data){
        var medico = repository.getReferenceById(data.id());
        medico.editarInformacoes(data);

        return ResponseEntity.ok(new DadosEditarDetalhadosMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirMedico(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhadosMedicoDTO(medico));
    }
}
