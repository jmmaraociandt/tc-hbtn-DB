package entities;

import javax.persistence.*;

@Entity
@Table(name = "materiais_curso")
public class MaterialCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @OneToOne(mappedBy = "materialCurso")
    private Curso curso;

    public MaterialCurso() {
    }

    public MaterialCurso(String url) {
        this.url = url;
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
