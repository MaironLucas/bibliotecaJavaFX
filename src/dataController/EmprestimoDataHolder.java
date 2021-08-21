/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataController;

import entidades.Livro;
import entidades.Usuario;

/**
 *
 * @author mairo
 */
public class EmprestimoDataHolder {
    private Usuario usuario;
    private Livro livro;
    
    public EmprestimoDataHolder(){
        usuario = null;
        usuario = null;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public void setLivro(Livro livro){
        this.livro = livro;
    }
    
    public Livro getLivro(){
        return this.livro;
    }
}
