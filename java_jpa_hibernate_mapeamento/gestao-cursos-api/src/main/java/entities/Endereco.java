package entities;

import javax.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer cep;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Endereco() {
    }

    public Endereco(String logradouro, String endereco, String numero, String bairro, String cidade, String estado, Integer cep) {
        this.logradouro = logradouro;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return logradouro + " " + endereco + ", " + numero + ", " + bairro + ", " + cidade +", " + estado + " - " + cep;
    }
}
