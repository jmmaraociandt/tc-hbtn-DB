package entities;

import javax.persistence.*;

@Entity
@Table(name = "telefones")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ddd;
    private String numero;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Telefone() {
    }

    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public Telefone(String ddd, String numero, Aluno aluno) {
        this.ddd = ddd;
        this.numero = numero;
        this.aluno = aluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return ddd + " " + numero;
    }
}
