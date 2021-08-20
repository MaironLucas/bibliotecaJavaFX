/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigosGerais;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author mairo
 */
public class MudarCena {
    public MudarCena (String caminhoFXML, BorderPane painel){
        try {
            Parent newScene = null;
            newScene = FXMLLoader.load(getClass().getClassLoader().getResource(caminhoFXML));
            painel.setCenter(newScene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
