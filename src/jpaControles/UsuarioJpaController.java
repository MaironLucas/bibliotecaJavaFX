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
import entidades.Emprestimo;
import entidades.Usuario;
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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getEmprestimoCollection() == null) {
            usuario.setEmprestimoCollection(new ArrayList<Emprestimo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Endereco IDEndereco = usuario.getIDEndereco();
            if (IDEndereco != null) {
                IDEndereco = em.getReference(IDEndereco.getClass(), IDEndereco.getIDEndereco());
                usuario.setIDEndereco(IDEndereco);
            }
            Collection<Emprestimo> attachedEmprestimoCollection = new ArrayList<Emprestimo>();
            for (Emprestimo emprestimoCollectionEmprestimoToAttach : usuario.getEmprestimoCollection()) {
                emprestimoCollectionEmprestimoToAttach = em.getReference(emprestimoCollectionEmprestimoToAttach.getClass(), emprestimoCollectionEmprestimoToAttach.getIDEmprestimo());
                attachedEmprestimoCollection.add(emprestimoCollectionEmprestimoToAttach);
            }
            usuario.setEmprestimoCollection(attachedEmprestimoCollection);
            em.persist(usuario);
            if (IDEndereco != null) {
                IDEndereco.getUsuarioCollection().add(usuario);
                IDEndereco = em.merge(IDEndereco);
            }
            for (Emprestimo emprestimoCollectionEmprestimo : usuario.getEmprestimoCollection()) {
                Usuario oldIDUsuarioOfEmprestimoCollectionEmprestimo = emprestimoCollectionEmprestimo.getIDUsuario();
                emprestimoCollectionEmprestimo.setIDUsuario(usuario);
                emprestimoCollectionEmprestimo = em.merge(emprestimoCollectionEmprestimo);
                if (oldIDUsuarioOfEmprestimoCollectionEmprestimo != null) {
                    oldIDUsuarioOfEmprestimoCollectionEmprestimo.getEmprestimoCollection().remove(emprestimoCollectionEmprestimo);
                    oldIDUsuarioOfEmprestimoCollectionEmprestimo = em.merge(oldIDUsuarioOfEmprestimoCollectionEmprestimo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIDUsuario());
            Endereco IDEnderecoOld = persistentUsuario.getIDEndereco();
            Endereco IDEnderecoNew = usuario.getIDEndereco();
            Collection<Emprestimo> emprestimoCollectionOld = persistentUsuario.getEmprestimoCollection();
            Collection<Emprestimo> emprestimoCollectionNew = usuario.getEmprestimoCollection();
            List<String> illegalOrphanMessages = null;
            for (Emprestimo emprestimoCollectionOldEmprestimo : emprestimoCollectionOld) {
                if (!emprestimoCollectionNew.contains(emprestimoCollectionOldEmprestimo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Emprestimo " + emprestimoCollectionOldEmprestimo + " since its IDUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (IDEnderecoNew != null) {
                IDEnderecoNew = em.getReference(IDEnderecoNew.getClass(), IDEnderecoNew.getIDEndereco());
                usuario.setIDEndereco(IDEnderecoNew);
            }
            Collection<Emprestimo> attachedEmprestimoCollectionNew = new ArrayList<Emprestimo>();
            for (Emprestimo emprestimoCollectionNewEmprestimoToAttach : emprestimoCollectionNew) {
                emprestimoCollectionNewEmprestimoToAttach = em.getReference(emprestimoCollectionNewEmprestimoToAttach.getClass(), emprestimoCollectionNewEmprestimoToAttach.getIDEmprestimo());
                attachedEmprestimoCollectionNew.add(emprestimoCollectionNewEmprestimoToAttach);
            }
            emprestimoCollectionNew = attachedEmprestimoCollectionNew;
            usuario.setEmprestimoCollection(emprestimoCollectionNew);
            usuario = em.merge(usuario);
            if (IDEnderecoOld != null && !IDEnderecoOld.equals(IDEnderecoNew)) {
                IDEnderecoOld.getUsuarioCollection().remove(usuario);
                IDEnderecoOld = em.merge(IDEnderecoOld);
            }
            if (IDEnderecoNew != null && !IDEnderecoNew.equals(IDEnderecoOld)) {
                IDEnderecoNew.getUsuarioCollection().add(usuario);
                IDEnderecoNew = em.merge(IDEnderecoNew);
            }
            for (Emprestimo emprestimoCollectionNewEmprestimo : emprestimoCollectionNew) {
                if (!emprestimoCollectionOld.contains(emprestimoCollectionNewEmprestimo)) {
                    Usuario oldIDUsuarioOfEmprestimoCollectionNewEmprestimo = emprestimoCollectionNewEmprestimo.getIDUsuario();
                    emprestimoCollectionNewEmprestimo.setIDUsuario(usuario);
                    emprestimoCollectionNewEmprestimo = em.merge(emprestimoCollectionNewEmprestimo);
                    if (oldIDUsuarioOfEmprestimoCollectionNewEmprestimo != null && !oldIDUsuarioOfEmprestimoCollectionNewEmprestimo.equals(usuario)) {
                        oldIDUsuarioOfEmprestimoCollectionNewEmprestimo.getEmprestimoCollection().remove(emprestimoCollectionNewEmprestimo);
                        oldIDUsuarioOfEmprestimoCollectionNewEmprestimo = em.merge(oldIDUsuarioOfEmprestimoCollectionNewEmprestimo);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Emprestimo> emprestimoCollectionOrphanCheck = usuario.getEmprestimoCollection();
            for (Emprestimo emprestimoCollectionOrphanCheckEmprestimo : emprestimoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Emprestimo " + emprestimoCollectionOrphanCheckEmprestimo + " in its emprestimoCollection field has a non-nullable IDUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
