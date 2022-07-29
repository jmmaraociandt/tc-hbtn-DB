package entities;

import javax.persistence.*;
import java.util.HashSet;
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
    @JoinColumn(name = "material_curso_id", referencedColumnName = "id")
    private MaterialCurso materialCurso;

    @ManyToMany
    @JoinTable(name = "curso_aluno",
            joinColumns = @JoinColumn(name = "aluno_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"))
    private Set<Aluno> alunos;

    public Curso() {
        this.alunos = new HashSet<>();
    }

    public Curso(String nome, String sigla, Professor professor, MaterialCurso materialCurso) {
        this.nome = nome;
        this.sigla = sigla;
        this.professor = professor;
        this.materialCurso = materialCurso;
        this.alunos = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setMaterialCurso(MaterialCurso materialCurso) {
        this.materialCurso = materialCurso;
    }

    @Override
    public String toString() {
        return "#" + id +
                " " + nome +
                " [" + sigla +
                "] " + professor +
                "\n\tCourse material:\n\t   " + materialCurso +
                "\n\t   Students:" + alunos;
    }
}
