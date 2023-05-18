package med.voll.api.paciente;

import med.voll.api.endereco.Endereco;

public record DadosEditarDetalhadosPacienteDTO(Long id, String nome, String telefone, Endereco endereco) {
    public DadosEditarDetalhadosPacienteDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEndereco());
    }
}
