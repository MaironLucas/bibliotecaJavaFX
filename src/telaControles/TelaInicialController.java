/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.MudarCena;
import CodigosGerais.Navegar;
import dataController.EmprestimoDataHolder;
import dataController.RenovacaoDataHolder;
import entidades.Emprestimo;
import entidades.Livro;
import entidades.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Button btRelatorio;

    private BorderPane root;
    @FXML
    private Button btEditarUser;
    @FXML
    private Button btEditarLivro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            root = (BorderPane) btEmprestar.getScene().getRoot();
        });
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
        root.setUserData(new EmprestimoDataHolder());
        new MudarCena("./telas/TelaEmprestimo.fxml", root);
    }

    @FXML
    private void chamaTelaRelatorio(ActionEvent event) {
        new MudarCena("./telas/TelaRelatorio.fxml", root);
    }

    @FXML
    private void chamarRenovar(ActionEvent event) {
        root.setUserData(new RenovacaoDataHolder());
        new MudarCena("./telas/TelaDevolucao.fxml", root);
    }

    @FXML
    private void chamarDevolver(ActionEvent event) {
        new MudarCena("./telas/TelaDevolucao.fxml", root);
    }

    @FXML
    private void chamaTelaEditarUsuario(ActionEvent event) {
        root.setUserData(new Usuario());
        new MudarCena("./telas/TelaEmprestimo.fxml", root);
    }

    @FXML
    private void chamaTelaEditarLivro(ActionEvent event) {
        root.setUserData(new Livro());
        new MudarCena("./telas/TelaEmprestimo2.fxml", root);
    }
}
