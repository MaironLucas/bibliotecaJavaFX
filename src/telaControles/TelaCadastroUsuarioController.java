/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.CaixaDeAlerta;
import CodigosGerais.DocumentoType;
import CodigosGerais.MotivoType;
import CodigosGerais.Navegar;
import CodigosGerais.StatusType;
import DAO.UsuarioDAO;
import entidades.Usuario;
import exceptions.ExceptionGenerica;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaCadastroUsuarioController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private Button btLimpar;
    @FXML
    private Button btSalvar;
    @FXML
    private TextField inputNome;
    @FXML
    private RadioButton rCPF;
    @FXML
    private RadioButton rRG;
    @FXML
    private RadioButton rEstudante;
    @FXML
    private TextField inputDocumento;
    @FXML
    private TextField inputTelefone;
    @FXML
    private TextField inputEmail;
    @FXML
    private RadioButton rAtivo;
    @FXML
    private RadioButton rInativo;
    @FXML
    private RadioButton rBloqueado;
    @FXML
    private ChoiceBox<String> btMotivo;
    @FXML
    private TextField inputRua;
    @FXML
    private TextField inputBairro;
    @FXML
    private TextField inputCidade;
    @FXML
    private TextField inputCEP;
    @FXML
    private Text textMotivo;
    @FXML
    private ChoiceBox<String> btUF;
    
    private ToggleGroup group1;
    private ToggleGroup group;
    @FXML
    private TextField inputNumero;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Populando a Choice Box de motivos
        btMotivo.getItems().addAll(FXCollections.observableArrayList(MotivoType.FALTA.getDescription(),
            MotivoType.LIVRO_DEFEITO.getDescription(), MotivoType.PROBLEMAS.getDescription()));
        
        //Populando choice box de UF
        btUF.getItems().addAll(FXCollections.observableArrayList("RS", "SC", "PR", "SP",
            "RJ", "ES", "BA", "MS", "MT", "GO", "TO", "AM", "AC", "AP", "AL", "CE",
            "MA", "MG", "PA", "PB", "PE", "PI", "RN", "RO", "RR", "SE", "DF"));
        
        //Criando o grupo de radio para documentos
        group = new ToggleGroup();
        
        rCPF.setText(DocumentoType.CPF.getDescription());
        rCPF.setUserData(DocumentoType.CPF);
        rCPF.setToggleGroup(group);
        
        rRG.setText(DocumentoType.RG.getDescription());
        rRG.setUserData(DocumentoType.RG);
        rRG.setToggleGroup(group);
        
        rEstudante.setText(DocumentoType.CEST.getDescription());
        rEstudante.setUserData(DocumentoType.CEST);
        rEstudante.setToggleGroup(group);
        
        rCPF.setSelected(true);
        
        //Criando o grupo de radio para causa de bloqueio
        group1 = new ToggleGroup();
        rAtivo.setText(StatusType.ATIVO.getDescription());
        rAtivo.setUserData(StatusType.ATIVO);
        rAtivo.setToggleGroup(group1);
        
        rInativo.setText(StatusType.INATIVO.getDescription());
        rInativo.setUserData(StatusType.INATIVO);
        rInativo.setToggleGroup(group1);
        
        rBloqueado.setText(StatusType.BLOQUEADO.getDescription());
        rBloqueado.setUserData(StatusType.BLOQUEADO);
        rBloqueado.setToggleGroup(group1);
        rAtivo.setSelected(true);
        
        //Deixando seleção de motivo invisivel
        textMotivo.setVisible(false);
        btMotivo.setVisible(false);
        
    }    

    @FXML
    private void chamarTelaInicial(ActionEvent event) {
        Navegar temp = new Navegar("./telas/TelaInicial.fxml", (Stage) btVoltar.getScene().getWindow());
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        //Limpa os campos de input
        inputNome.clear();
        inputBairro.clear();
        inputCEP.clear();
        inputCidade.clear();
        inputEmail.clear();
        inputRua.clear();
        inputTelefone.clear();
        inputDocumento.clear();
        inputNumero.clear();
        textMotivo.setVisible(false);
        rCPF.setSelected(true);
        rAtivo.setSelected(true);
        btMotivo.setVisible(false);
    }

    @FXML
    private void salvarInformacoes(ActionEvent event) {
        Usuario usuario = new Usuario();
        boolean controle = false;
        try{
            usuario.setNome(inputNome.getText());
            usuario.setEmail(inputEmail.getText());
            if (rBloqueado.isSelected()){
                String descricaoBotao = btMotivo.getSelectionModel().getSelectedItem();
                switch (descricaoBotao){
                    case "Falta na entrega":usuario.setMotivo(1);break;
                    case "Livro devolvido com defeito":usuario.setMotivo(2);break;
                    case "Problemas cadastrais":usuario.setMotivo(3);break;
                }
            } else {
                usuario.setMotivo(null);
            }
            usuario.setNumDoc(inputDocumento.getText());
            DocumentoType tipoDocTemp = (DocumentoType) group.getSelectedToggle().getUserData();
            usuario.setTipoDoc(tipoDocTemp.getIndex());
            usuario.setTelefone(inputTelefone.getText());
            StatusType statusTemp = (StatusType) group1.getSelectedToggle().getUserData();
            usuario.setStatus(statusTemp.getIndex());
            usuario.setBairro(inputBairro.getText());
            usuario.setCep(inputCEP.getText());
            usuario.setCidade(inputCidade.getText());
            usuario.setRua(inputRua.getText());
            usuario.setNumero(inputNumero.getText());
            usuario.setUf(btUF.getSelectionModel().getSelectedItem());
            System.out.println(usuario);
            controle = true;  
        } catch(ExceptionGenerica e){
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Falha no input", e.getMessage());
        }
        
        if (controle){
            try{
                UsuarioDAO usuarioPersist = new UsuarioDAO();
                usuarioPersist.add(usuario);
            } catch (Exception e){
                new CaixaDeAlerta(Alert.AlertType.ERROR, "Erro", e.getMessage());
            }
        }
    }

    @FXML
    private void ativoSelecionado(ActionEvent event) {
        btMotivo.setVisible(false);
        textMotivo.setVisible(false);
    }

    @FXML
    private void inativoSelecionado(ActionEvent event) {
        btMotivo.setVisible(false);
        textMotivo.setVisible(false);
    }

    @FXML
    private void bloqueadoSelecionado(ActionEvent event) {
        btMotivo.setVisible(true);
        textMotivo.setVisible(true);
    }
    
}
