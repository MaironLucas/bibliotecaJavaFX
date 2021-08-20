/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigosGerais;

import javafx.scene.control.Alert;

/**
 *
 * @author mairo
 */
public class CaixaDeAlerta {
    private Alert a;
    
    public CaixaDeAlerta(Alert.AlertType tipo, String titulo, String mensagem){
        Alert a = new Alert(tipo);
        a.setTitle(titulo);
        a.setContentText(mensagem);
        a.show();
    }
}
