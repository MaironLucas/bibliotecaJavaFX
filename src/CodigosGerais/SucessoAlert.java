/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigosGerais;

import javafx.scene.control.Alert;

/**
 *
 * @author galva
 */
public class SucessoAlert {

    private Alert a;

    public SucessoAlert(String mensagem) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Sucesso");
        a.setHeaderText("Sucesso");
        a.setContentText(mensagem);
        //a.setOnCloseRequest(eh);
        a.show();
    }
}
