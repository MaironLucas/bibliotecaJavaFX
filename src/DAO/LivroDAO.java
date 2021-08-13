/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Livro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaControles.LivroJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author mairo
 */
public class LivroDAO {
    private final LivroJpaController objetoJPA;
    private final EntityManagerFactory emf;
    
    public LivroDAO(){
        emf = Persistence.createEntityManagerFactory("ProjetoFinalPU");
        objetoJPA = new LivroJpaController(emf);
    }
    
    public void add(Livro objeto) throws Exception{
        objetoJPA.create(objeto);
    }
    
    public void edit(Livro objeto) throws Exception{
        objetoJPA.edit(objeto);
    }
    
    public void remove(Integer id) throws NonexistentEntityException, IllegalOrphanException{
        objetoJPA.destroy(id);
    }
    
    public List<Livro> getAllUsuarios(){
        return objetoJPA.findLivroEntities();
    }
    
    public void persist(Livro objeto){
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
