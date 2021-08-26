/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataController;

import entidades.Emprestimo;

/**
 *
 * @author mairo
 */
public class RenovacaoDataHolder {
    private Emprestimo emprestimo;
    
    public RenovacaoDataHolder(){
        this.emprestimo = null;
    }
    
    public void setEmprestimo(Emprestimo emprestimo){
        this.emprestimo = emprestimo;
    }
    
    public Emprestimo getEmprestimo(){
        return this.emprestimo;
    }
}
