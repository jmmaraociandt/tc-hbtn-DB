package models;

import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {
    private static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        return emf.createEntityManager();
    }

    public void create(Pessoa p) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        EntityManager em = getEntityManager();

        try {
            if (findById(p) != null) {
                System.out.println("Iniciando a transação");
                em.getTransaction().begin();
                em.merge(p);
                em.getTransaction().commit();
                System.out.println("Pessoa alterada com sucesso !!!");
            }
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao alterar pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Pessoa p) {
        EntityManager em = getEntityManager();

        try {
            if (findById(p) != null) {
                System.out.println("Iniciando a transação");
                em.getTransaction().begin();
                em.createQuery("DELETE FROM Pessoa WHERE id = :id")
                        .setParameter("id", p.getId())
                        .executeUpdate();
                em.getTransaction().commit();
                System.out.println("Pessoa removida com sucesso !!!");
            }
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa findById(Pessoa p) {
        EntityManager em = getEntityManager();

        return em.find(Pessoa.class, p.getId());
    }

    @SuppressWarnings("unchecked")
    public List<Pessoa> findAll() {
        EntityManager em = getEntityManager();
        List<Pessoa> people = new ArrayList<Pessoa>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            people = em.createQuery("FROM Pessoa").getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao listar pessoas !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return people;
    }
}
