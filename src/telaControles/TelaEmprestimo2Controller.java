/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.CaixaDeAlerta;
import CodigosGerais.MudarCena;
import CodigosGerais.Navegar;
import DAO.LivroDAO;
import dataController.EmprestimoDataHolder;
import entidades.Livro;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    @FXML
    private TextField inputBusca;
    private BorderPane root;
    @FXML
    private HBox userBox;

    private boolean edicao;
    @FXML
    private Text textUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Definicao das colunas
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autoresCol.setCellValueFactory(new PropertyValueFactory<>("autores"));
        exemplaresCol.setCellValueFactory(new PropertyValueFactory<>("qtdexemplares"));
        emprestadosCol.setCellValueFactory(new PropertyValueFactory<>("qtdemprestados"));
        
        //Difinindo qual tela chamou (edicao de livro ou emprestimo)
        Platform.runLater(() -> {
            root = (BorderPane) btBuscar.getScene().getRoot();
            if (root.getUserData() instanceof Livro) {
                edicao = true;
                userBox.setVisible(false);
            } else {
                edicao = false;
                emprestimo = (EmprestimoDataHolder) root.getUserData();
                usuarioSel = emprestimo.getUsuario();
                labelUsuario.setText(usuarioSel.getNome());
            }
        });
    }

    @FXML
    private void buscarLivro(ActionEvent event) {
        //Busca no banco pelas informa??oes passadas
        LivroDAO livroDao = new LivroDAO();
        ObservableList<Livro> listaDeLivros = FXCollections.observableArrayList();
        listaDeLivros.addAll(livroDao.getbyTitulo(inputBusca.getText()));
        listaDeLivros.addAll(livroDao.getByIsbn(inputBusca.getText()));
        tabelaLivros.setItems(listaDeLivros);
    }

    @FXML
    private void alteraCapa(MouseEvent event) {
        //Altera a capa de acordo com o emprestimo selecionado
        Livro temp = (Livro) tabelaLivros.getSelectionModel().getSelectedItem();
        if (temp != null) {
            Image tempImage = new Image(temp.getCapa());
            capaView.setImage(tempImage);
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        if (edicao)
            new MudarCena("./telas/TelaInicial.fxml", root);
        else
            new MudarCena("./telas/TelaEmprestimo.fxml", root);
    }

    @FXML
    private void avancarParaEmprestimo(ActionEvent event) {
        if (event.getSource() == btEditar)
            edicao = true;
        //Faz as verificacoes (se foi selecionado, se ?? edicao de livro)
        Livro livroSel = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSel == null) {
            new CaixaDeAlerta(Alert.AlertType.WARNING, "Falha de Sele????o", "Um livro deve ser selecionado!");
        } else {
            if (!edicao){
                if (livroSel.getQtdexemplares() == livroSel.getQtdemprestados()){
                    new CaixaDeAlerta(Alert.AlertType.WARNING, "Falha na sele????o", "O livro selecionado n??o possui estoque");
                } else{
                    emprestimo.setLivro(livroSel);
                    root.setUserData(emprestimo);
                    new MudarCena("./telas/TelaEmprestimo3.fxml", root);
                }      
            } else{
                Stage stagetemp = (Stage) btVoltar.getScene().getWindow();
                stagetemp.setUserData(livroSel);
                new Navegar("./telas/TelaCadastroLivro.fxml", (Stage) root.getScene().getWindow());
            }

        }
    }
}
