/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.MudarCena;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author galva
 */
public class TelaRelatorioController implements Initializable {

    @FXML
    private Button btVoltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void chamaVoltarInicial(ActionEvent event) {
        BorderPane root = (BorderPane) btVoltar.getScene().getRoot();
        new MudarCena("./telas/TelaInicial.fxml", root);
    }

}
