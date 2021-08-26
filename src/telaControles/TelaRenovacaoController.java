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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaRenovacaoController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private Button btnAvancar;
    @FXML
    private Label labelTitulo;
    @FXML
    private Label labelIsbn;
    @FXML
    private Label labelNome;
    @FXML
    private Text textTipoDoc;
    @FXML
    private Label labelDoc;
    @FXML
    private ChoiceBox<String> btTempo;
    
    private BorderPane root;
    private Emprestimo emprestimo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btTempo.getItems().addAll(FXCollections.observableArrayList("15 Dias", "30 Dias"));
        btTempo.getSelectionModel().select("15 Dias");
        
        Platform.runLater(() -> {
            root = (BorderPane) btVoltar.getScene().getRoot();
            emprestimo = (Emprestimo) root.getUserData();
            povoarUsuario();
            povoarLivro();
        });
    }    

    @FXML
    private void voltar(ActionEvent event) {
    }

    @FXML
    private void avancarParaTicket(ActionEvent event) {
        int diasDeEmprestimo;
        //Convertendo para dias de emprestimo
        if (btTempo.getSelectionModel().getSelectedItem().equals("15 Dias"))
            diasDeEmprestimo = 15;
        else
            diasDeEmprestimo = 30;
        try{
            //Acrescentando dias ao emprestimo
            emprestimo.setTempoDeEmprestimo(emprestimo.getTempoDeEmprestimo() + diasDeEmprestimo);

            //Convertendo de Date para localDate
            Date dataPrazo = emprestimo.getPrazo();
            LocalDate temp = dataPrazo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            //Incrementando os dias ao prazo
            temp = temp.plusDays(diasDeEmprestimo);

            //Convertendo para Date e gravando no banco
            emprestimo.setPrazo(Date.from(temp.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            
            //Pegando a data
            LocalDate data = LocalDate.now();
            data.format(DateTimeFormatter.ISO_LOCAL_DATE);
            
            //Adicionando observacao
            emprestimo.setObservacoes("Renovado em " + data.toString() + "por " + diasDeEmprestimo + " dias.");
            
            //Gravando no banco
            try{
                EmprestimoDAO emprestimoDao = new EmprestimoDAO();
                emprestimoDao.edit(emprestimo);
                RenovacaoDataHolder renovacao = new RenovacaoDataHolder();
                renovacao.setEmprestimo(emprestimo);
                root.setUserData(renovacao);
                new MudarCena("./telas/TelaTicket.fxml", root);
            } catch (Exception e){
                new CaixaDeAlerta(Alert.AlertType.ERROR, "Falha no banco", e.getMessage());
            }
        } catch(Exception e){
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Falha na conversão", e.getMessage());
        }
    }

    private void povoarUsuario(){
        labelNome.setText(emprestimo.getIDUsuario().getNome());
        textTipoDoc.setText(DocumentoType.getDescriptionByIndex(emprestimo.getIDUsuario().getTipoDoc()) + ":");
        labelDoc.setText(emprestimo.getIDUsuario().getNumDoc());
    }
    
    private void povoarLivro(){
        labelTitulo.setText(emprestimo.getIDLivro().getTitulo());
        labelIsbn.setText(emprestimo.getIDLivro().getIsbn());
    }
    
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
}
}
