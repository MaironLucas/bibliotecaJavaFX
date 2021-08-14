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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaCadastroUsuarioController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private Button btLimpar;
    @FXML
    private Button btSalvar;
    @FXML
    private TextField inputNome;
    @FXML
    private RadioButton rCPF;
    @FXML
    private RadioButton rRG;
    @FXML
    private RadioButton rEstudante;
    @FXML
    private TextField inputDocumento;
    @FXML
    private TextField inputTelefone;
    @FXML
    private TextField inputEmail;
    @FXML
    private RadioButton rAtivo;
    @FXML
    private RadioButton rInativo;
    @FXML
    private RadioButton rBloqueado;
    @FXML
    private ChoiceBox<?> btMotivo;
    @FXML
    private TextField inputRua;
    @FXML
    private TextField inputBairro;
    @FXML
    private TextField inputCidade;
    @FXML
    private TextField inputUF;
    @FXML
    private TextField inputCEP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chamarTelaInicial(ActionEvent event) {
        try {
            Stage stage = null;
            Parent newScene = null;
            stage = (Stage) btVoltar.getScene().getWindow();
            newScene = FXMLLoader.load(getClass().getClassLoader().getResource("./telas/TelaInicial.fxml"));
            Scene scene = new Scene(newScene);
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("Lamentavel");
        }
    }

    @FXML
    private void limparCampos(ActionEvent event) {
    }

    @FXML
    private void salvarInformacoes(ActionEvent event) {
    }
    
}
