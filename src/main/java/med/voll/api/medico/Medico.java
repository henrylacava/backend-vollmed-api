package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded // Classe separada, mas no banco de dados considera fazer parte da mesma tabela da tabela de medicos
    private Endereco endereco;

    public Medico(MedicoCadastrarDTO data){
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.crm = data.crm();
        this.especialidade = data.especialidade();
        this.endereco = new Endereco(data.endereco());
    }

    public void editarInformacoes(MedicoEditarDTO data) {
        if (data.nome() != null){
            this.nome = data.nome();
        }
        if (data.telefone() != null){
            this.telefone = data.telefone();
        }
        if (data.endereco() != null){
            this.endereco.editarInformacoes(data.endereco());
        }
    }
}
