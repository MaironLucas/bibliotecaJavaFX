/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Endereco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaControles.EnderecoJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author mairo
 */
public class EnderecoDAO {
    private final EnderecoJpaController objetoJPA;
    private final EntityManagerFactory emf;
    
    public EnderecoDAO(){
        emf = Persistence.createEntityManagerFactory("ProjetoFinalPU");
        objetoJPA = new EnderecoJpaController(emf);
    }
    
    public void add(Endereco objeto) throws Exception{
        objetoJPA.create(objeto);
    }
    
    public void edit(Endereco objeto) throws Exception{
        objetoJPA.edit(objeto);
    }
    
    public void remove(Integer id) throws NonexistentEntityException, IllegalOrphanException{
        objetoJPA.destroy(id);
    }
    
    public List<Endereco> getAllUsuarios(){
        return objetoJPA.findEnderecoEntities();
    }
    
    public void persist(Endereco objeto){
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
