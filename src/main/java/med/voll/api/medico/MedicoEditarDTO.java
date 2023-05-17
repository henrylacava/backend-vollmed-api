package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.EnderecoRequestDTO;

public record MedicoEditarDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRequestDTO endereco) {
}
