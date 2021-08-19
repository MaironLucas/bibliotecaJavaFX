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
import entidades.Endereco;
import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author mairo
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Endereco IDEndereco = usuario.getIDEndereco();
            if (IDEndereco != null) {
                IDEndereco = em.getReference(IDEndereco.getClass(), IDEndereco.getIDEndereco());
                usuario.setIDEndereco(IDEndereco);
            }
            em.persist(usuario);
            if (IDEndereco != null) {
                IDEndereco.getUsuarioCollection().add(usuario);
                IDEndereco = em.merge(IDEndereco);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIDUsuario());
            Endereco IDEnderecoOld = persistentUsuario.getIDEndereco();
            Endereco IDEnderecoNew = usuario.getIDEndereco();
            if (IDEnderecoNew != null) {
                IDEnderecoNew = em.getReference(IDEnderecoNew.getClass(), IDEnderecoNew.getIDEndereco());
                usuario.setIDEndereco(IDEnderecoNew);
            }
            usuario = em.merge(usuario);
            if (IDEnderecoOld != null && !IDEnderecoOld.equals(IDEnderecoNew)) {
                IDEnderecoOld.getUsuarioCollection().remove(usuario);
                IDEnderecoOld = em.merge(IDEnderecoOld);
            }
            if (IDEnderecoNew != null && !IDEnderecoNew.equals(IDEnderecoOld)) {
                IDEnderecoNew.getUsuarioCollection().add(usuario);
                IDEnderecoNew = em.merge(IDEnderecoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIDUsuario();
                if (findUsuario(id) == null) {
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIDUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Endereco IDEndereco = usuario.getIDEndereco();
            if (IDEndereco != null) {
                IDEndereco.getUsuarioCollection().remove(usuario);
                IDEndereco = em.merge(IDEndereco);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
