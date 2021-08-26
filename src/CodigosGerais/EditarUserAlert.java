/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigosGerais;

import entidades.Usuario;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author mairo
 */
public class EditarUserAlert {
    private Alert a;
    
    public EditarUserAlert(Stage stage, Usuario usuario){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Problemas cadastrais");
        a.setContentText("O úsuario selecionado apresenta problemas, necessário editar!");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK){
            stage.setUserData(usuario);
            Navegar temp = new Navegar("./telas/TelaCadastroUsuario.fxml", stage);
        } else{
            new MudarCena("./telas/Menu.fxml", (BorderPane) stage.getScene().getRoot());
        }
    }
}
