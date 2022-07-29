package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;
    private String matricula;
    private LocalDate nascimento;
    private String email;

    @ManyToMany(mappedBy = "alunos")
    private Set<Curso> cursos;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private Set<Endereco> enderecos;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private Set<Telefone> telefones;

    public Aluno() {
        this.cursos = new HashSet<>();
        this.enderecos = new HashSet<>();
        this.telefones = new HashSet<>();
    }

    public Aluno(String nomeCompleto, String matricula, LocalDate nascimento, String email) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
        this.cursos = new HashSet<>();
        this.enderecos = new HashSet<>();
        this.telefones = new HashSet<>();
    }

    public Aluno(Long id, String nomeCompleto, String matricula, LocalDate nascimento, String email) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
        this.cursos = new HashSet<>();
        this.enderecos = new HashSet<>();
        this.telefones = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return "#" + id +
                " " + nomeCompleto +
                " [" + matricula +
                "]\tBirth date: " + nascimento +
                "\n\tCourses: \n\t   " + cursos +
                "\n\tContacts:\n\t   email: " + email +
                "\n\t   Adress: " + enderecos +
                "\n\t   Phone: " + telefones;
    }
}
