package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedicos(@RequestBody @Valid MedicoCadastrarDTO data){
        repository.save(new Medico(data));
    }

    @GetMapping
    public Page<MedicoResponseDTO> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginate){
        return repository.findAll(paginate).map(MedicoResponseDTO::new);
    }

    @PutMapping
    @Transactional
    public void editarMedico(@RequestBody @Valid MedicoEditarDTO data){
        var medico = repository.getReferenceById(data.id());
        medico.editarInformacoes(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirMedico(@PathVariable Long id){
        repository.deleteById(id);
    }
}
