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
        cursos = new HashSet<>();
        enderecos = new HashSet<>();
        telefones = new HashSet<>();
    }

    public Aluno(String nomeCompleto, String matricula, LocalDate nascimento, String email) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
    }

    public Aluno(String nomeCompleto, String matricula, LocalDate nascimento, String email, Set<Curso> cursos, Set<Endereco> enderecos, Set<Telefone> telefones) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
        this.cursos = cursos;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public Aluno(Long id, String nomeCompleto, String matricula, LocalDate nascimento, String email, Set<Endereco> enderecos, Set<Telefone> telefones) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    //TODO DELETAR
    public Aluno(String nomeCompleto, String matricula, LocalDate nascimento, String email, Set<Endereco> enderecos, Set<Telefone> telefones) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public Aluno(String nomeCompleto, String matricula, LocalDate nascimento, String email, Set<Curso> cursos) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.nascimento = nascimento;
        this.email = email;
        this.cursos = cursos;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                " [" + nomeCompleto +
                "] " + matricula +
                " | Birth date: " + nascimento +
                "\n\tCourses: \n\t   " + cursos +
                "\n\tContacts:\n\t   email: " + email +
                "\n\t   Adress: " + enderecos +
                "\n\t   Phone: " + telefones;
    }
}
