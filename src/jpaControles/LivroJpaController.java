/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaControles;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Emprestimo;
import entidades.Livro;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author mairo
 */
public class LivroJpaController implements Serializable {

    public LivroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Livro livro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Livro livro) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            livro = em.merge(livro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = livro.getIDLivro();
                if (findLivro(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livro livro;
            try {
                livro = em.getReference(Livro.class, id);
                livro.getIDLivro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The livro with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Emprestimo> emprestimoCollectionOrphanCheck = livro.getEmprestimoCollection();
            for (Emprestimo emprestimoCollectionOrphanCheckEmprestimo : emprestimoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Livro (" + livro + ") cannot be destroyed since the Emprestimo " + emprestimoCollectionOrphanCheckEmprestimo + " in its emprestimoCollection field has a non-nullable IDLivro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(livro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Livro> findLivroEntities() {
        return findLivroEntities(true, -1, -1);
    }

    public List<Livro> findLivroEntities(int maxResults, int firstResult) {
        return findLivroEntities(false, maxResults, firstResult);
    }

    private List<Livro> findLivroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Livro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Livro findLivro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Livro.class, id);
        } finally {
            em.close();
        }
    }

    public int getLivroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Livro> rt = cq.from(Livro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Livro> findByTitulo(String titulo){
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Query q = em.createNamedQuery("Livro.findByTitulo", Livro.class);//em.createQuery(cq);
            q.setParameter("titulo", "%" + titulo + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Livro> findByIsbn(String isbn){
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Query q = em.createNamedQuery("Livro.findByIsbn", Livro.class);//em.createQuery(cq);
            q.setParameter("isbn", isbn);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
