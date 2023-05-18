package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.EnderecoRequestDTO;

public record PacienteEditarDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRequestDTO endereco) {

}
