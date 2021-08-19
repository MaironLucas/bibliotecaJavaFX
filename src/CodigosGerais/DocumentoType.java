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
public enum DocumentoType {
    CPF("CPF", 1),
    RG("RG", 2),
    CEST("Carteira de Estudante", 3);
    
    private String description;
    private Integer index;
    
    DocumentoType(String description, Integer index){
        this.description = description;
        this.index = index;
    }
    
    public String getDescription(){
        return description;
    }
    
    public Integer getIndex(){
        return index;
    }
}
