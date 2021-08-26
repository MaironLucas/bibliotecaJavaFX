/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.DocumentoType;
import CodigosGerais.MudarCena;
import dataController.EmprestimoDataHolder;
import entidades.Emprestimo;
import entidades.Livro;
import entidades.Usuario;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaDevolucao2Controller implements Initializable {

    @FXML
    private Label labelNome;
    @FXML
    private Label labelDocumento;
    @FXML
    private Label labelNumDoc;
    @FXML
    private Label labelTelefone;
    @FXML
    private Label labelTitulo;
    @FXML
    private Label labelISBN;
    @FXML
    private Label labelAutores;
    @FXML
    private DatePicker pickData;
    @FXML
    private ImageView userFoto;
    @FXML
    private ImageView capaLivro;
    @FXML
    private ChoiceBox<?> btMotivo;
    @FXML
    private Label labelPrazo;
    
    private BorderPane root;
    private Usuario usuarioSel;
    private Livro livroSel;
    private Emprestimo emprestimo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate data = LocalDate.now();
        data.format(DateTimeFormatter.ISO_LOCAL_DATE);
        
        pickData.setValue(data);
        
        Platform.runLater(() -> {
            root = (BorderPane) btMotivo.getScene().getRoot();
            emprestimo = (Emprestimo) root.getUserData();
            usuarioSel = emprestimo.getIDUsuario();
            livroSel = emprestimo.getIDLivro();
            povoarUsuario();
            povoarLivro();
        });
    }    

    @FXML
    private void avancarParaTicket(ActionEvent event) {
        new MudarCena("./telas/TelaTicket.fxml", root);
    }

    @FXML
    private void voltarParaSelecao(ActionEvent event) {
        new MudarCena("./telas/TelaDevolucao.fxml", root);
    }
    
    private void povoarUsuario(){
        labelNome.setText(usuarioSel.getNome());
        labelDocumento.setText(DocumentoType.getDescriptionByIndex(usuarioSel.getTipoDoc()) + ":");
        labelNumDoc.setText(usuarioSel.getNumDoc());
        labelTelefone.setText(usuarioSel.getTelefone());
        Image temp = new Image("./assets/defaultUser.png");
        userFoto.setImage(temp);
    }
    
    private void povoarLivro(){
        labelTitulo.setText(livroSel.getTitulo());
        labelISBN.setText(livroSel.getIsbn());
        labelAutores.setText(livroSel.getAutores());
        Image temp = new Image(livroSel.getCapa());
        capaLivro.setImage(temp);
    }
}
