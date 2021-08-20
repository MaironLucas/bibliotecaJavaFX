/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import DAO.UsuarioDAO;
import entidades.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
        
        UsuarioDAO usuariodao = new UsuarioDAO();
        ObservableList<Usuario> listaDeUsuarios = FXCollections.observableArrayList();
        listaDeUsuarios.addAll(usuariodao.getByNome("Mairon"));
        tabelaDeUsuarios.setItems(listaDeUsuarios);
    }    

    @FXML
    private void chamarTelaInicial(ActionEvent event) {
        
    }

    @FXML
    private void limparCampos(ActionEvent event) {
    }

    @FXML
    private void salvarLivro(ActionEvent event) {
    }

    @FXML
    private void buscarUsuarios(ActionEvent event) {
        
    }
    
}
