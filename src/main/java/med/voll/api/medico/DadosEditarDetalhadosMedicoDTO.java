package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

public record DadosEditarDetalhadosMedicoDTO(Long id, String nome, String telefone, Endereco endereco) {
    public DadosEditarDetalhadosMedicoDTO(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getTelefone(), medico.getEndereco());
    }
}
