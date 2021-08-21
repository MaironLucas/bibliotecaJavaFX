/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import dataController.EmprestimoDataHolder;
import entidades.Livro;
import entidades.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaEmprestimo2Controller implements Initializable {

    @FXML
    private Button btBuscar;
    @FXML
    private Button btVoltar;
    @FXML
    private Button btEditar;
    @FXML
    private Button btAvancar;
    @FXML
    private Label labelUsuario;
    @FXML
    private ImageView capaView;
    @FXML
    private TableView<Livro> tabelaLivros;
    @FXML
    private TableColumn<Livro, String> isbnCol;
    @FXML
    private TableColumn<Livro, String> tituloCol;
    @FXML
    private TableColumn<Livro, String> autoresCol;
    @FXML
    private TableColumn<Livro, Integer> exemplaresCol;
    @FXML
    private TableColumn<Livro, Integer> emprestadosCol;
    
    private EmprestimoDataHolder emprestimo;
    private Usuario usuarioSel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            BorderPane root = (BorderPane) btBuscar.getScene().getRoot();
            emprestimo = (EmprestimoDataHolder) root.getUserData();
            usuarioSel = emprestimo.getUsuario();
            labelUsuario.setText(usuarioSel.getNome());
        });
    }    

    @FXML
    private void buscarLivro(ActionEvent event) {
        
    }
    
}
