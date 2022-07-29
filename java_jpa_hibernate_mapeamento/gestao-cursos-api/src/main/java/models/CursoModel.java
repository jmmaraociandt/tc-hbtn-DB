package models;

import com.techcamps.gestao.cursos.entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CursoModel implements AutoCloseable {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
    private static EntityManager em = emf.createEntityManager();

    public void create(Curso course) {
        try {
            System.out.println("Starting transaction...");
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            System.out.println("Course created successfully!");
            System.out.println("Ending transaction...\n");
        } catch (Exception e) {
            System.err.println("Error to create a course! " + e.getMessage());
        }
    }

    @Override
    public void close() {
        if (emf != null && em != null) {
            emf.close();
            em.close();
            System.out.println("Connection to SQLite has been closed.");
        }
    }
}
