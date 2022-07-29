package models;

import com.techcamps.gestao.cursos.entities.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class AlunoModel implements AutoCloseable {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
    private static EntityManager em = emf.createEntityManager();

    public void create(Aluno student) {
        try {
            System.out.println("Starting transaction...");
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            System.out.println("Student created successfully!");
            System.out.println("Ending transaction...\n");
        } catch (Exception e) {
            System.err.println("Error to create a student! " + e.getMessage());
        }
    }

    public Aluno findById(Long id) {
        return em.find(Aluno.class, id);
    }

    public void update(Aluno student) {
        try {
            if (findById(student.getId()) != null) {
                System.out.println("Starting transaction...");
                em.getTransaction().begin();
                em.merge(student);
                em.getTransaction().commit();
                System.out.println("Student updated successfully!");
                System.out.println("Ending transaction...\n");
            } else
                System.out.println("Student not found!");
        } catch (Exception e) {
            System.err.println("Error to create a student! " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Aluno> findAll() {
        List<Aluno> students = new ArrayList<>();
        try {
            System.out.println("Starting transaction...");
            em.getTransaction().begin();
            students = em.createQuery("FROM " + Aluno.class.getName()).getResultList();
            em.getTransaction().commit();
            System.out.println("Students recovered successfully!");
            System.out.println("Ending transaction...\n");

        } catch (Exception e) {
            System.err.println("Error to recover students! " + e.getMessage());
        }
        return students;
    }

    public void delete(Aluno student) {
        try {
            if (findById(student.getId()) != null) {
                System.out.println("Starting transaction...");
                em.getTransaction().begin();
                em.remove(student);
                em.getTransaction().commit();
                System.out.println("Student deleted successfully!");
                System.out.println("Ending transaction...\n");
            } else
                System.out.println("Student not found!");
        } catch (Exception e) {
            System.err.println("Error to create a student! " + e.getMessage());
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
