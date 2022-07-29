package entities;

import javax.persistence.*;

@Entity
@Table(name = "materiais_curso")
public class MaterialCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @OneToOne
    @JoinColumn(name = "material_curso_id")
    private Curso curso;

    public MaterialCurso() {
    }

    public MaterialCurso(String url, Curso curso) {
        this.url = url;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "MaterialCurso{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", curso=" + curso +
                '}';
    }
}
