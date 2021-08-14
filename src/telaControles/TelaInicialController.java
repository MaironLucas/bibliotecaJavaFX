/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaInicialController implements Initializable {

    @FXML
    private Button btCadastrarLivro;
    @FXML
    private Button btCadastrarUsuario;
    @FXML
    private Button btEmprestar;
    @FXML
    private Button btRenovar;
    @FXML
    private Button btDevolver;
    @FXML
    private MenuItem menuCadastrarLivro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chamarCadastroLivro(ActionEvent event) {
        try {
            Stage stage = null;
            Parent newScene = null;
            stage = (Stage) btCadastrarLivro.getScene().getWindow();
            newScene = FXMLLoader.load(getClass().getClassLoader().getResource("./telas/TelaCadastroLivro.fxml"));
            Scene scene = new Scene(newScene);
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("Lamentavel");
        }
    }

    @FXML
    private void chamarCadastrarUsuario(ActionEvent event) {
        try {
            Stage stage = null;
            Parent newScene = null;
            stage = (Stage) btCadastrarLivro.getScene().getWindow();
            newScene = FXMLLoader.load(getClass().getClassLoader().getResource("./telas/TelaCadastroUsuario.fxml"));
            Scene scene = new Scene(newScene);
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("Lamentavel");
        }
    }
    
}
