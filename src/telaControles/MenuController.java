/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.MudarCena;
import CodigosGerais.Navegar;
import dataController.RenovacaoDataHolder;
import entidades.Livro;
import entidades.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author galva
 */
public class MenuController implements Initializable {

    @FXML
    private MenuItem menuInicio;
    @FXML
    private BorderPane root;
    @FXML
    private MenuItem menuLivro;
    @FXML
    private MenuItem menuCadastrarUsuario;
    @FXML
    private MenuItem menuEditarLivro;
    @FXML
    private MenuItem chamaEditarLivro;
    @FXML
    private MenuItem menuRelatorio;

    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
            stage = (Stage) root.getScene().getWindow();
            new MudarCena("./telas/TelaInicial.fxml", root);
        });
    }

    @FXML
    private void chamarCadastroLivro(ActionEvent event) {
        stage.setUserData(null);
        new Navegar("./telas/TelaCadastroLivro.fxml", (Stage) root.getScene().getWindow());
    }

    @FXML
    private void chamaCadastrarUsuario(ActionEvent event) {
        stage.setUserData(null);
        Navegar temp = new Navegar("./telas/TelaCadastroUsuario.fxml", (Stage) root.getScene().getWindow());
    }

    @FXML
    private void chamarEmprestarLivro(ActionEvent event) {
        new MudarCena("./telas/TelaEmprestimo.fxml", root);
    }

    @FXML
    private void chamaEditarLivro(ActionEvent event) {
        root.setUserData(new Livro());
        new MudarCena("./telas/TelaEmprestimo2.fxml", root);
    }

    @FXML
    private void chamarEditarUsuario(ActionEvent event) {
        root.setUserData(new Usuario());
        new MudarCena("./telas/TelaEmprestimo.fxml", root);
    }

    @FXML
    private void chamarTelaRelatorio(ActionEvent event) {
        new MudarCena("./telas/TelaRelatorio.fxml", root);
    }

    @FXML
    private void chamaTelaInicial(ActionEvent event) {
        new MudarCena("./telas/TelaInicial.fxml", root);
    }

    @FXML
    private void chamarDevolver(ActionEvent event) {
        new MudarCena("./telas/TelaDevolucao.fxml", root);
    }

    @FXML
    private void chamarRenovar(ActionEvent event) {
        root.setUserData(new RenovacaoDataHolder());
        new MudarCena("./telas/TelaDevolucao.fxml", root);
    }

}
