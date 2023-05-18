package med.voll.api.paciente;

public record PacienteReponseDTO(Long id, String nome, String email, String cpf) {
    public PacienteReponseDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
