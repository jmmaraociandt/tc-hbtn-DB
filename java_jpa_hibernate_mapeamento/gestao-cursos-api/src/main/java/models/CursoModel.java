package models;

import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CursoModel implements AutoCloseable {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
    private static EntityManager em = emf.createEntityManager();

    public void create(Curso course) {
        try {
            System.out.println("Starting transaction...");
            em.getTransaction().begin();
            System.out.println("course = " + course);
            em.persist(course);
            em.getTransaction().commit();
            System.out.println("Course created successfully!");
            System.out.println("Ending transaction...\n");
        } catch (Exception e) {
            System.err.println("Error to create a course! " + e.getMessage());
        }
    }

    public Curso findById(Long id) {
        return em.find(Curso.class, id);
    }

    public void update(Curso course) {
        try {
            if (findById(course.getId()) != null) {
                System.out.println("Starting transaction...");
                em.getTransaction().begin();
                em.merge(course);
                em.getTransaction().commit();
                System.out.println("Course updated successfully!");
                System.out.println("Ending transaction...\n");
            } else
                System.out.println("Course not found!");
        } catch (Exception e) {
            System.err.println("Error to update a course! " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Curso> findAll() {
        List<Curso> courses = new ArrayList<>();
        try {
            System.out.println("Starting transaction...");
            em.getTransaction().begin();
            courses = em.createQuery("FROM " + Curso.class.getName()).getResultList();
            em.getTransaction().commit();
            System.out.println("Courses recovered successfully!");
            System.out.println("Ending transaction...\n");

        } catch (Exception e) {
            System.err.println("Error to recover courses! " + e.getMessage());
        }
        return courses;
    }

    public void delete(Curso course) {
        try {
            if (findById(course.getId()) != null) {
                System.out.println("Starting transaction...");
                em.getTransaction().begin();
                em.remove(course);
                em.getTransaction().commit();
                System.out.println("Course deleted successfully!");
                System.out.println("Ending transaction...\n");
            } else
                System.out.println("Course not found!");
        } catch (Exception e) {
            System.err.println("Error to delete a course! " + e.getMessage());
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
