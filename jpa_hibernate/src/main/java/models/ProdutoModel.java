package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ProdutoModel {
    private static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        return emf.createEntityManager();
    }

    public void create(Produto p) {
        EntityManager em = getEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManager em = getEntityManager();

        try {
            if (findById(p) != null) {
                System.out.println("Iniciando a transação");
                em.getTransaction().begin();
                em.merge(p);
                em.getTransaction().commit();
                System.out.println("Produto alterado com sucesso !!!");
            }
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao alterar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Produto p) {
        EntityManager em = getEntityManager();

        try {
            if (findById(p) != null) {
                System.out.println("Iniciando a transação");
                em.getTransaction().begin();
                em.createQuery("DELETE FROM Produto WHERE id = :id")
                        .setParameter("id", p.getId())
                        .executeUpdate();
                em.getTransaction().commit();
                System.out.println("Produto removido com sucesso !!!");
            }
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto findById(Produto p) {
        EntityManager em = getEntityManager();

        return em.find(Produto.class, p.getId());
    }

    @SuppressWarnings("unchecked")
    public List<Produto> findAll() {
        EntityManager em = getEntityManager();
        List<Produto> produtos = new ArrayList<Produto>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            produtos = em.createQuery("FROM Produto").getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao listar produtos !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return produtos;
    }
}
