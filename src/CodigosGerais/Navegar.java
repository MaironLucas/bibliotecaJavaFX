/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigosGerais;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mairo
 */
public class Navegar {
    
    public Navegar(String caminhoFXML, Stage stage){
        try {
            Parent newScene = null;
            newScene = FXMLLoader.load(getClass().getClassLoader().getResource(caminhoFXML));
            Scene scene = new Scene(newScene);
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("Lamentavel");
        }
    }
}
