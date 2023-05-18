package med.voll.api.paciente;

import med.voll.api.endereco.Endereco;

public record DadosDetalhadosPacienteDTO(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {
    public DadosDetalhadosPacienteDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
