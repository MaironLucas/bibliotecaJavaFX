/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.MudarCena;
import CodigosGerais.Navegar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
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
    @FXML
    private BorderPane root;
    @FXML
    private MenuItem menuEditarLivro;
    @FXML
    private MenuItem menuEditarUsuario;
    @FXML
    private Menu menuInicio;
    @FXML
    private Menu menuRelatorio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chamarCadastroLivro(ActionEvent event) {
        Navegar temp = new Navegar("./telas/TelaCadastroLivro.fxml", (Stage) root.getScene().getWindow());
    }

    @FXML
    private void chamarCadastrarUsuario(ActionEvent event) {
        Navegar temp = new Navegar("./telas/TelaCadastroUsuario.fxml", (Stage) root.getScene().getWindow());
    }

    @FXML
    private void chamarEmprestarLivro(ActionEvent event) {
        new MudarCena("./telas/TelaEmprestimo.fxml", root);
    }
    
    @FXML
    private void chamaEditarLivro(ActionEvent event) {
        new MudarCena("./telas/TelaEditarLivro.fxml", root);
    }

    @FXML
    private void chamarEditarUsuario(ActionEvent event) {
        new MudarCena("./telas/TelaEditarUsuario.fxml", root);
    }

    @FXML
    private void chamarTelaRelatorio(ActionEvent event) {
    }
}
