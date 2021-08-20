/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import entidades.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

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
    private ListView<?> tabelaDeUsuarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn nameCol = new TableColumn("Nome");
        TableColumn tipoDocCol = new TableColumn("Tipo de Doc");
        TableColumn numDocCol = new TableColumn("Número Doc");
        TableColumn telefoneCol = new TableColumn("Telefone");
        TableColumn fotoCol = new TableColumn("Foto");
        
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
