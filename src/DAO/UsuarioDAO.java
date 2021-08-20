/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaControles.UsuarioJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author mairo
 */
public class UsuarioDAO {
    private final UsuarioJpaController objetoJPA;
    private final EntityManagerFactory emf;
    
    public UsuarioDAO(){
        emf = Persistence.createEntityManagerFactory("ProjetoFinalPU");
        objetoJPA = new UsuarioJpaController(emf);
    }
    
    public void add(Usuario objeto) throws Exception{
        objetoJPA.create(objeto);
    }
    
    public void edit(Usuario objeto) throws Exception{
        objetoJPA.edit(objeto);
    }
    
    public void remove(Integer id) throws NonexistentEntityException, IllegalOrphanException{
        objetoJPA.destroy(id);
    }
    
    public List<Usuario> getAllUsuarios(){
        return objetoJPA.findUsuarioEntities();
    }
    
    public List<Usuario> getByNome(String nome){
        return objetoJPA.findByNome(nome);
    }
    
    public List<Usuario> getByNumDoc(String numDoc){
        return objetoJPA.findByNumDoc(numDoc);
    }
    
    public void persist(Usuario objeto){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally{
            em.close();
        }
    }
}
