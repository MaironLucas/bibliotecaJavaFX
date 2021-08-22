/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.DocumentoType;
import dataController.EmprestimoDataHolder;
import entidades.Emprestimo;
import entidades.Livro;
import entidades.Usuario;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
public class TelaEmprestimo3Controller implements Initializable {

    @FXML
    private Label labelNome;
    @FXML
    private Label labelDocumento;
    @FXML
    private Label labelNumDoc;
    @FXML
    private Label labelTelefone;
    @FXML
    private ImageView userFoto;
    @FXML
    private Label labelTitulo;
    @FXML
    private Label labelISBN;
    @FXML
    private Label labelAutores;
    @FXML
    private ImageView capaLivro;
    @FXML
    private DatePicker pickData;
    @FXML
    private ChoiceBox<String> btTempo;
    
    private BorderPane root;
    private EmprestimoDataHolder emprestimo;
    private Usuario usuarioSel;
    private Livro livroSel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btTempo.getItems().addAll(FXCollections.observableArrayList("15 Dias", "30 Dias"));
        btTempo.getSelectionModel().select("15 Dias");
        
        LocalDate data = LocalDate.now();
        data.format(DateTimeFormatter.ISO_LOCAL_DATE);
        
        pickData.setValue(data);
        
        Platform.runLater(() -> {
            root = (BorderPane) btTempo.getScene().getRoot();
            emprestimo = (EmprestimoDataHolder) root.getUserData();
            usuarioSel = emprestimo.getUsuario();
            livroSel = emprestimo.getLivro();
            povoarUsuario();
            povoarLivro();
        });
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

    @FXML
    private void avancarParaTicket(ActionEvent event) {
        LocalDate date = pickData.getValue();
        Emprestimo emprestimo = new Emprestimo();
        int diasDeEmprestimo;
        //Convertendo para dias de emprestimo
        if (btTempo.getSelectionModel().getSelectedItem().equals("15 Dias"))
            diasDeEmprestimo = 15;
        else
            diasDeEmprestimo = 30;
        
        //definicao dos campos do emprestimo
        emprestimo.setTempoDeEmprestimo(diasDeEmprestimo);
        emprestimo.setIDLivro(livroSel);
        emprestimo.setIDUsuario(usuarioSel);
        
        //conversao de LocalDate para Date
        Date date1 = new Date();
        date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        emprestimo.setDataEmprestimo(date1);
        System.out.println(date1);
        
        //Calculando data de devolucao
        date = date.plusDays(diasDeEmprestimo);
        date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        emprestimo.setDataDevolucao(date1);
        System.out.println(date1);
        
        
    }
}
