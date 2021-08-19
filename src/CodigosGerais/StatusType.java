/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigosGerais;

/**
 *
 * @author mairo
 */
public enum StatusType {
    ATIVO("Ativo", 1),
    INATIVO("Inativo", 2),
    BLOQUEADO("Bloqueado", 3);
    
    private String description;
    private Integer index;
    
    StatusType(String description, Integer index){
        this.description = description;
        this.index = index;
    }
    
    public Integer getIndex(){
        return index;
    }
    
    public String getDescription(){
        return description;
    }
}
