/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.CaixaDeAlerta;
import CodigosGerais.DocumentoType;
import CodigosGerais.MotivoType;
import CodigosGerais.MudarCena;
import DAO.EmprestimoDAO;
import DAO.LivroDAO;
import DAO.UsuarioDAO;
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
import javafx.scene.control.Alert;
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
    private ChoiceBox<String> btMotivo;
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
        //Pegando data do sistema
        LocalDate data = LocalDate.now();
        data.format(DateTimeFormatter.ISO_LOCAL_DATE);
        
        //Definindo dataPicker para dia atual
        pickData.setValue(data);
        
        //Criando choiceBox de motivos
        btMotivo.getItems().addAll(FXCollections.observableArrayList(MotivoType.FALTA.getDescription(),
                MotivoType.LIVRO_DEFEITO.getDescription(), MotivoType.PROBLEMAS.getDescription(), "Não"));
        
        btMotivo.getSelectionModel().selectLast();
        
        Platform.runLater(() -> {
            root = (BorderPane) btMotivo.getScene().getRoot();
            emprestimo = (Emprestimo) root.getUserData();
            usuarioSel = emprestimo.getIDUsuario();
            livroSel = emprestimo.getIDLivro();
            povoarUsuario();
            povoarLivro();
            
            //Escrevendo label de prazo
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            labelPrazo.setText(format.format(emprestimo.getPrazo()));
        });
    }    

    @FXML
    private void avancarParaTicket(ActionEvent event) {
        //Pegando data selecionada
        LocalDate dataTemp = pickData.getValue();
        
        //Pegando prazo
        Date prazo = emprestimo.getPrazo();
        
        //Convertendo de LocalDate para Date
        Date dateDevolucao = new Date();
        dateDevolucao = Date.from(dataTemp.atStartOfDay(ZoneId.systemDefault()).toInstant());
        emprestimo.setDataDevolucao(dateDevolucao);
        
        //Verificando se o usuario precisa ser bloqueado
        String motivoSel = btMotivo.getSelectionModel().getSelectedItem();
        if (!motivoSel.equals("Não")){
            emprestimo.getIDUsuario().setMotivo(MotivoType.getIndexByDescription(motivoSel));
            emprestimo.getIDUsuario().setStatus(3);
        }
        
        //Verificando necessidade de multa
        if (dateDevolucao.after(prazo)){
            new CaixaDeAlerta(Alert.AlertType.INFORMATION, "Multa", "Deve ser cobrada multa pelo atraso!");
            emprestimo.setObservacoes("O livro " + livroSel.getTitulo() + " foi devolvido com atraso por " + usuarioSel.getNome());
        } else{
            emprestimo.setObservacoes("O livro " + livroSel.getTitulo() + " foi devolvido dentro do prazo por " + usuarioSel.getNome());
        }
        
        //Decrementando quantidade de livros emprestados
        Livro temp = emprestimo.getIDLivro();
        temp.setQtdemprestados(temp.getQtdemprestados() - 1);
        
        //Lancando emprestimo, usuario e livro no banco
        try{
            UsuarioDAO usuarioDao = new UsuarioDAO();
            EmprestimoDAO emprestimoDao = new EmprestimoDAO();
            LivroDAO livroDao = new LivroDAO();
            usuarioDao.edit(emprestimo.getIDUsuario());
            emprestimoDao.edit(emprestimo);
            livroDao.edit(temp);
            root.setUserData(emprestimo);
            new MudarCena("./telas/TelaTicket.fxml", root);
        } catch (Exception e){
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Erro no banco", e.getMessage());
        } 
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
