/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.MudarCena;
import entidades.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaEmprestimo2Controller implements Initializable {

    @FXML
    private Button btVoltar;

    private Usuario usuarioSel;
    @FXML
    private Button btBuscar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Entrou memo"); 
        BorderPane root = (BorderPane) btVoltar.getScene().getRoot();
        //usuarioSel = (Usuario) cena.getUserData();  
    }    

    @FXML
    private void voltarParaUsuario(ActionEvent event) {
        new MudarCena("./telas/TelaEmprestimo.fxml", (BorderPane) btVoltar.getScene().getRoot());
    }
    
}
