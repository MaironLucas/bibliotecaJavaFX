/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.CaixaDeAlerta;
import CodigosGerais.DocumentoType;
import CodigosGerais.MudarCena;
import dataController.EmprestimoDataHolder;
import dataController.RenovacaoDataHolder;
import entidades.Emprestimo;
import entidades.Livro;
import entidades.Usuario;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaTicketController implements Initializable {

    @FXML
    private Button btImprimir;
    @FXML
    private Text txtNome;
    @FXML
    private Text txtTipoDoc;
    @FXML
    private Text txtNumDoc;
    @FXML
    private Text txtTelefone;
    @FXML
    private Text txtEmail;
    @FXML
    private Text txtIsbn;
    @FXML
    private Text txtAutores;
    @FXML
    private Text txtDataEmprestimo;
    @FXML
    private Text txtDataDevolucao;
    @FXML
    private Text txtTempo;
    @FXML
    private VBox caixaCentral;
    @FXML
    private Text txtTipoData;
    @FXML
    private Text txtTituloPainel;
    @FXML
    private Text txtTitulo;
    
    private BorderPane root;
    private Usuario usuarioSel;
    private Livro livroSel;
    private Emprestimo emprestimo;
    private boolean devolucaoBol;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            root = (BorderPane) btImprimir.getScene().getRoot();
            if (root.getUserData() instanceof EmprestimoDataHolder){
                EmprestimoDataHolder emprestimoData;
                emprestimoData = (EmprestimoDataHolder) root.getUserData();
                usuarioSel = emprestimoData.getUsuario();
                livroSel = emprestimoData.getLivro();
                emprestimo = emprestimoData.getEmprestimo();
                txtTituloPainel.setText("Comprovante de Empréstimo");
                System.out.println("Emprestimo");
                devolucaoBol = false;
            } else{
                if (root.getUserData() instanceof RenovacaoDataHolder){
                    RenovacaoDataHolder renovacaoD;
                    renovacaoD = (RenovacaoDataHolder) root.getUserData();
                    emprestimo = renovacaoD.getEmprestimo();
                    usuarioSel = emprestimo.getIDUsuario();
                    livroSel = emprestimo.getIDLivro();
                    txtTituloPainel.setText("Comprovante de Renovação");
                    System.out.println("Renovacao");
                    devolucaoBol = false;
                } else{
                    emprestimo = (Emprestimo) root.getUserData();
                    usuarioSel = emprestimo.getIDUsuario();
                    livroSel = emprestimo.getIDLivro();
                    txtTituloPainel.setText("Comprovante de Devolução");
                    System.out.println("Devolucao");
                    devolucaoBol = true;
                }
            }
            povoarUsuario();
            povoarLivro();
            povoarEmprestimo();
        });
    }    
    
    private void povoarUsuario(){
        txtNome.setText(usuarioSel.getNome());
        txtTipoDoc.setText(DocumentoType.getDescriptionByIndex(usuarioSel.getTipoDoc()) + ":");
        txtNumDoc.setText(usuarioSel.getNumDoc());
        txtTelefone.setText(usuarioSel.getTelefone());
        txtEmail.setText(usuarioSel.getEmail() == null ? usuarioSel.getEmail() : "email não informado");
    }
    
    private void povoarLivro(){
        txtTitulo.setText(livroSel.getTitulo());
        txtIsbn.setText(livroSel.getIsbn());
        txtAutores.setText(livroSel.getAutores());
    }
    
    private void povoarEmprestimo(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        txtDataEmprestimo.setText(format.format(emprestimo.getDataEmprestimo()));
        txtTipoData.setText(devolucaoBol ? "Data de Devolução:" : "Prazo para Devolução:");
        txtDataDevolucao.setText(devolucaoBol ? format.format(emprestimo.getDataDevolucao()) : format.format(emprestimo.getPrazo()));
        txtTempo.setText(Integer.toString(emprestimo.getTempoDeEmprestimo()));
    }
    
    @FXML
    private void solicitarImpressao(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        job.showPrintDialog((Stage) btImprimir.getScene().getWindow());
        job.showPageSetupDialog((Stage) btImprimir.getScene().getWindow());
        
        boolean sucess = job.printPage(caixaCentral);
        if (sucess){
            job.endJob();
        } else{
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Falha na impressão", "Não foi possível concluir a operação!");
            job.endJob();
        }
    }

    @FXML
    private void finalizarEmprestimo(ActionEvent event) {
        new MudarCena("./telas/TelaInicial.fxml", root);
    }
    
}
