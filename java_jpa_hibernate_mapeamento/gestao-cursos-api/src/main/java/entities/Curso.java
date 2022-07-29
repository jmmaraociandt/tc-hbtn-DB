package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sigla;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToOne
    @JoinColumn(name = "material_curso_id")
    private MaterialCurso materialCurso;

    @ManyToMany
    private Set<Aluno> alunos;

    public Curso() {
    }

    public Curso(String nome, String sigla, Professor professor, MaterialCurso materialCurso, Set<Aluno> alunos) {
        this.nome = nome;
        this.sigla = sigla;
        this.professor = professor;
        this.materialCurso = materialCurso;
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", professor=" + professor +
                ", materialCurso=" + materialCurso +
                ", alunos=" + alunos +
                '}';
    }
}
