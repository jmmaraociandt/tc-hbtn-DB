package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;
    private String matricula;
    private String email;

    @OneToMany(mappedBy = "professor")
    private Set<Curso> cursos;

    public Professor() {
        cursos = new HashSet<>();
    }

    public Professor(String nomeCompleto, String matricula, String email, Set<Curso> cursos) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.email = email;
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", matricula='" + matricula + '\'' +
                ", email='" + email + '\'' +
                ", cursos=" + cursos +
                '}';
    }
}
