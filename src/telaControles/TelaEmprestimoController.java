/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.CaixaDeAlerta;
import CodigosGerais.MudarCena;
import CodigosGerais.Navegar;
import DAO.UsuarioDAO;
import dataController.EmprestimoDataHolder;
import entidades.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaEmprestimoController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private Button btnAvancar;
    @FXML
    private TextField inputBusca;
    @FXML
    private TableView<Usuario> tabelaDeUsuarios;
    @FXML
    private TableColumn<Usuario, String> fotoCol;
    @FXML
    private TableColumn<Usuario, String> nomeCol;
    @FXML
    private TableColumn<Usuario, String> tipoDocCol;
    @FXML
    private TableColumn<Usuario, String> numeroDocCol;
    @FXML
    private TableColumn<Usuario, String> telefoneCol;

    private UsuarioDAO usuarioDao;

    private BorderPane root;

    private boolean edicao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fotoCol.setCellValueFactory(new PropertyValueFactory<>("foto"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipoDocCol.setCellValueFactory(new PropertyValueFactory<>("tipoDoc"));
        numeroDocCol.setCellValueFactory(new PropertyValueFactory<>("numDoc"));
        telefoneCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        Platform.runLater(() -> {
            root = (BorderPane) btVoltar.getScene().getRoot();
            if (root.getUserData() instanceof Usuario) {
                edicao = true;
            } else {
                edicao = false;
            }
        });
    }

    @FXML
    private void chamarTelaInicial(ActionEvent event) {
        new MudarCena("./telas/TelaInicial.fxml", (BorderPane) btVoltar.getScene().getRoot());
    }

    @FXML
    private void limparCampos(ActionEvent event) {
    }

    @FXML
    private void buscarUsuarios(ActionEvent event) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        ObservableList<Usuario> listaDeUsuarios = FXCollections.observableArrayList();
        listaDeUsuarios.addAll(usuarioDao.getByNumDoc(inputBusca.getText()));
        listaDeUsuarios.addAll(usuarioDao.getByNome(inputBusca.getText()));
        tabelaDeUsuarios.setItems(listaDeUsuarios);
    }

    @FXML
    private void avancarParaLivro(ActionEvent event) {
        Usuario usuarioSel = tabelaDeUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSel == null) {
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Falha de inserção", "Um usuário deve ser selecionado!");
        } else {
            BorderPane root = (BorderPane) btVoltar.getScene().getRoot();
            if (edicao) {
                Stage stagetemp = (Stage) btVoltar.getScene().getWindow();
                stagetemp.setUserData(usuarioSel);
                Navegar temp = new Navegar("./telas/TelaCadastroUsuario.fxml", (Stage) root.getScene().getWindow());
            } else {
                EmprestimoDataHolder emprestimo = new EmprestimoDataHolder();
                emprestimo.setUsuario(usuarioSel);
                root.setUserData(emprestimo);
                new MudarCena("./telas/TelaEmprestimo2.fxml", root);
            }

        }
    }

}
