/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.Navegar;
import entidades.Endereco;
import entidades.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Populando a Choice Box de motivos
        btMotivo.getItems().addAll(FXCollections.observableArrayList("Falta na entrega",
            "Livro devolvido com defeito", "Problemas cadastrais"));
        
        //Populando choice box de UF
        btUF.getItems().addAll(FXCollections.observableArrayList("RS", "SC", "PR", "SP",
            "RJ", "ES", "BA", "MS", "MT", "GO", "TO", "AM", "AC", "AP", "AL", "CE",
            "MA", "MG", "PA", "PB", "PE", "PI", "RN", "RO", "RR", "SE", "DF"));
        
        //Criando o grupo de radio para documentos
        group = new ToggleGroup();
        rCPF.setToggleGroup(group);
        rRG.setToggleGroup(group);
        rEstudante.setToggleGroup(group);
        rCPF.setSelected(true);
        
        //Criando o grupo de radio para causa de bloqueio
        group1 = new ToggleGroup();
        rAtivo.setToggleGroup(group1);
        rInativo.setToggleGroup(group1);
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
        inputNome.clear();
        inputBairro.clear();
        inputCEP.clear();
        inputCidade.clear();
        inputEmail.clear();
        inputRua.clear();
        inputTelefone.clear();
        inputDocumento.clear();
        textMotivo.setVisible(false);
        rCPF.setSelected(true);
        rAtivo.setSelected(true);
        btMotivo.setVisible(false);
    }

    @FXML
    private void salvarInformacoes(ActionEvent event) {
        try{
            Usuario usuario = new Usuario();
            Endereco endereco = new Endereco();
            usuario.setNome(inputNome.getText());
            usuario.setEmail(inputEmail.getText());
            if (rBloqueado.isSelected())
                usuario.setMotivo(btMotivo.getSelectionModel().getSelectedItem());
            else
                usuario.setMotivo(null);
            RadioButton temp = (RadioButton) group.getSelectedToggle();
            usuario.setTipoDoc(temp.getText());
            usuario.setNumDoc(inputDocumento.getText());
            usuario.setTelefone(inputTelefone.getText());
            endereco.setBairro(inputBairro.getText());
            endereco.setCep(inputCEP.getText());
            endereco.setCidade(inputCidade.getText());
            endereco.setRua(inputRua.getText());
            endereco.setUf(btUF.getSelectionModel().getSelectedItem());
            System.out.println(usuario);
            System.out.println(endereco);
        } catch(Exception e){
            System.out.println("F");
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
