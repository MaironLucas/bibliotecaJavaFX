/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaControles;

import entidades.Emprestimo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Livro;
import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author mairo
 */
public class EmprestimoJpaController implements Serializable {

    public EmprestimoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Emprestimo emprestimo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livro IDLivro = emprestimo.getIDLivro();
            if (IDLivro != null) {
                IDLivro = em.getReference(IDLivro.getClass(), IDLivro.getIDLivro());
                emprestimo.setIDLivro(IDLivro);
            }
            Usuario IDUsuario = emprestimo.getIDUsuario();
            if (IDUsuario != null) {
                IDUsuario = em.getReference(IDUsuario.getClass(), IDUsuario.getIDUsuario());
                emprestimo.setIDUsuario(IDUsuario);
            }
            em.persist(emprestimo);
            if (IDLivro != null) {
                IDLivro.getEmprestimoCollection().add(emprestimo);
                IDLivro = em.merge(IDLivro);
            }
            if (IDUsuario != null) {
                IDUsuario.getEmprestimoCollection().add(emprestimo);
                IDUsuario = em.merge(IDUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Emprestimo emprestimo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emprestimo persistentEmprestimo = em.find(Emprestimo.class, emprestimo.getIDEmprestimo());
            Livro IDLivroOld = persistentEmprestimo.getIDLivro();
            Livro IDLivroNew = emprestimo.getIDLivro();
            Usuario IDUsuarioOld = persistentEmprestimo.getIDUsuario();
            Usuario IDUsuarioNew = emprestimo.getIDUsuario();
            if (IDLivroNew != null) {
                IDLivroNew = em.getReference(IDLivroNew.getClass(), IDLivroNew.getIDLivro());
                emprestimo.setIDLivro(IDLivroNew);
            }
            if (IDUsuarioNew != null) {
                IDUsuarioNew = em.getReference(IDUsuarioNew.getClass(), IDUsuarioNew.getIDUsuario());
                emprestimo.setIDUsuario(IDUsuarioNew);
            }
            emprestimo = em.merge(emprestimo);
            if (IDLivroOld != null && !IDLivroOld.equals(IDLivroNew)) {
                IDLivroOld.getEmprestimoCollection().remove(emprestimo);
                IDLivroOld = em.merge(IDLivroOld);
            }
            if (IDLivroNew != null && !IDLivroNew.equals(IDLivroOld)) {
                IDLivroNew.getEmprestimoCollection().add(emprestimo);
                IDLivroNew = em.merge(IDLivroNew);
            }
            if (IDUsuarioOld != null && !IDUsuarioOld.equals(IDUsuarioNew)) {
                IDUsuarioOld.getEmprestimoCollection().remove(emprestimo);
                IDUsuarioOld = em.merge(IDUsuarioOld);
            }
            if (IDUsuarioNew != null && !IDUsuarioNew.equals(IDUsuarioOld)) {
                IDUsuarioNew.getEmprestimoCollection().add(emprestimo);
                IDUsuarioNew = em.merge(IDUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = emprestimo.getIDEmprestimo();
                if (findEmprestimo(id) == null) {
                    throw new NonexistentEntityException("The emprestimo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emprestimo emprestimo;
            try {
                emprestimo = em.getReference(Emprestimo.class, id);
                emprestimo.getIDEmprestimo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emprestimo with id " + id + " no longer exists.", enfe);
            }
            Livro IDLivro = emprestimo.getIDLivro();
            if (IDLivro != null) {
                IDLivro.getEmprestimoCollection().remove(emprestimo);
                IDLivro = em.merge(IDLivro);
            }
            Usuario IDUsuario = emprestimo.getIDUsuario();
            if (IDUsuario != null) {
                IDUsuario.getEmprestimoCollection().remove(emprestimo);
                IDUsuario = em.merge(IDUsuario);
            }
            em.remove(emprestimo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Emprestimo> findEmprestimoEntities() {
        return findEmprestimoEntities(true, -1, -1);
    }

    public List<Emprestimo> findEmprestimoEntities(int maxResults, int firstResult) {
        return findEmprestimoEntities(false, maxResults, firstResult);
    }

    private List<Emprestimo> findEmprestimoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Emprestimo.class));
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

    public Emprestimo findEmprestimo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emprestimo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmprestimoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Emprestimo> rt = cq.from(Emprestimo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
