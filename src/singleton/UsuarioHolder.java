/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import entidades.Usuario;

/**
 *
 * @author mairo
 */
public final class UsuarioHolder {
    private Usuario usuario;
    private final static UsuarioHolder INSTANCE = new UsuarioHolder();
    
    private UsuarioHolder(){}
    
    public static UsuarioHolder getInstance(){
        return INSTANCE;
    }
    
    public void setUsuario(Usuario u){
        this.usuario = u;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
}
