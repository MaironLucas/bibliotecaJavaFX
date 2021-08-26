/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.CaixaDeAlerta;
import CodigosGerais.DocumentoType;
import CodigosGerais.MudarCena;
import DAO.EmprestimoDAO;
import dataController.RenovacaoDataHolder;
import entidades.Emprestimo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaDevolucaoController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private Button btnAvancar;
    @FXML
    private TextField inputBusca;
    @FXML
    private TableView<Emprestimo> tabelaDeEmprestimos;
    @FXML
    private TableColumn<Emprestimo, String> tituloCol;
    @FXML
    private TableColumn<Emprestimo, String> isbnCol;
    @FXML
    private TableColumn<Emprestimo, String> nomeCol;
    @FXML
    private TableColumn<Emprestimo, String> tipoDocCol;
    @FXML
    private TableColumn<Emprestimo, String> numeroDocCol;
    
    private BorderPane root;
    private boolean isRenovacao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isbnCol.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getIDLivro().getIsbn()));
        tituloCol.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getIDLivro().getTitulo()));
        nomeCol.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getIDUsuario().getNome()));
        tipoDocCol.setCellValueFactory((param) -> new SimpleStringProperty(DocumentoType.getDescriptionByIndex(param.getValue().getIDUsuario().getTipoDoc())));
        numeroDocCol.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getIDUsuario().getNumDoc()));
        
        Platform.runLater(() -> {
            root = (BorderPane) btVoltar.getScene().getRoot();
            if (root.getUserData() instanceof RenovacaoDataHolder){
                isRenovacao = true;
                System.out.println("Renovacao");
            } else{
                isRenovacao = false;
                System.out.println("Devolucao");
            }
            root.setUserData(null);
        });
    }    

    @FXML
    private void chamarTelaInicial(ActionEvent event) {
        root.setUserData(null);
        new MudarCena("./telas/TelaInicial.fxml", root);
    }

    @FXML
    private void avancarParaDevolucao(ActionEvent event) {
        Emprestimo emprestimoSel = tabelaDeEmprestimos.getSelectionModel().getSelectedItem();
        if (emprestimoSel == null){
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Falha de Seleção", "Um emprestimo deve ser selecionado!");
        } else{
            root.setUserData(emprestimoSel);
            new MudarCena("./telas/TelaDevolucao2.fxml", root);
        }  
    }

    @FXML
    private void buscarEmprestimos(ActionEvent event) {
        EmprestimoDAO emprestimoDao = new EmprestimoDAO();
        ObservableList<Emprestimo> listaDeEmprestimos = FXCollections.observableArrayList();
        listaDeEmprestimos.addAll(emprestimoDao.getEmprestados(inputBusca.getText()));
        tabelaDeEmprestimos.setItems(listaDeEmprestimos);
    }
    
}
