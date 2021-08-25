/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Emprestimo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaControles.EmprestimoJpaController;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author mairo
 */
public class EmprestimoDAO {

    private final EmprestimoJpaController objetoJPA;
    private final EntityManagerFactory emf;

    public EmprestimoDAO() {
        emf = Persistence.createEntityManagerFactory("ProjetoFinalPU");
        objetoJPA = new EmprestimoJpaController(emf);
    }

    public void add(Emprestimo objeto) throws Exception {
        objetoJPA.create(objeto);
    }

    public void edit(Emprestimo objeto) throws Exception {
        objetoJPA.edit(objeto);
    }

    public void remove(Integer id) throws NonexistentEntityException, IllegalOrphanException {
        objetoJPA.destroy(id);
    }

    public List<Emprestimo> getAllUsuarios() {
        return objetoJPA.findEmprestimoEntities();
    }

    public void persist(Emprestimo objeto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Emprestimo> getEmprestados(String busca) {
        return objetoJPA.findEmprestados(busca, busca, busca, busca);
    }

    public List<Emprestimo> getAllEmprestados() {
        return objetoJPA.findAllEmprestados();
    }
}
