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
public enum MotivoType {
    FALTA("Falta na entrega", 1),
    LIVRO_DEFEITO("Livro devolvido com defeito", 2),
    PROBLEMAS("Problemas cadastrais", 3);
    
    private String description;
    private Integer index;
    
    MotivoType(String description, Integer index){
        this.description = description;
        this.index = index;
    }
    
    public String getDescription(){
        return description;
    }
    
    public static int getIndexByDescription(String desc){
        for (MotivoType d : MotivoType.values()){
            if(d.description.equals(desc)){
                return d.index;
            }       
        }
        return 0;
    }
    
    public Integer getIndex(){
        return index;
    }
}
