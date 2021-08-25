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
import CodigosGerais.SucessoAlert;
import DAO.UsuarioDAO;
import dataController.EmprestimoDataHolder;
import entidades.Usuario;
import exceptions.ExceptionGenerica;
import java.net.URL;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
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

    private Usuario usuarioSel;
    private boolean edicao;
    @FXML
    private Label textTitulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            Stage stagetemp = (Stage) btVoltar.getScene().getWindow();
            usuarioSel = (Usuario) stagetemp.getUserData();
            if (usuarioSel != null) {
                edicao = true;
                textTitulo.setText("Edição de Usuário");
                povoarCampos();
            } else {
                edicao = false;
            }
        });

        //Populando a Choice Box de motivos
        btMotivo.getItems().addAll(FXCollections.observableArrayList(MotivoType.FALTA.getDescription(),
                MotivoType.LIVRO_DEFEITO.getDescription(), MotivoType.PROBLEMAS.getDescription()));

        //Populando choice box de UF
        btUF.getItems().addAll(FXCollections.observableArrayList("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"));

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
        Navegar temp = new Navegar("./telas/Menu.fxml", (Stage) btVoltar.getScene().getWindow());
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
        try {
            usuario.setNome(inputNome.getText());
            usuario.setEmail(inputEmail.getText());
            if (rBloqueado.isSelected()) {
                String descricaoBotao = btMotivo.getSelectionModel().getSelectedItem();
                switch (descricaoBotao) {
                    case "Falta na entrega":
                        usuario.setMotivo(1);
                        break;
                    case "Livro devolvido com defeito":
                        usuario.setMotivo(2);
                        break;
                    case "Problemas cadastrais":
                        usuario.setMotivo(3);
                        break;
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
        } catch (ExceptionGenerica e) {
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Falha no input", e.getMessage());
        }

        if (controle) {
            try {
                UsuarioDAO usuarioPersist = new UsuarioDAO();
                if (!edicao) {
                    usuarioPersist.add(usuario);
                    new SucessoAlert("Usuário cadastrado com sucesso");
                } else {
                    usuario.setIDUsuario(usuarioSel.getIDUsuario());
                    usuarioPersist.edit(usuario);
                    new SucessoAlert("Usuário editado com sucesso");
                }
                new Navegar("./telas/Menu.fxml", (Stage) btVoltar.getScene().getWindow());
            } catch (Exception e) {
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

    private void povoarCampos() {
        inputNome.setText(usuarioSel.getNome());
        inputBairro.setText(usuarioSel.getBairro());
        inputCEP.setText(usuarioSel.getCep());
        inputCidade.setText(usuarioSel.getCidade());
        inputRua.setText(usuarioSel.getRua());
        inputTelefone.setText(usuarioSel.getTelefone());
        inputDocumento.setText(usuarioSel.getNumDoc());
        inputNumero.setText(usuarioSel.getNumero());
        // EMAIL
        if (usuarioSel.getEmail() != null) {
            inputEmail.setText(usuarioSel.getEmail());
        } else {
            inputEmail.clear();
        }
        // STATUS/MOTIVO
        if (usuarioSel.getMotivo() != null) {
            textMotivo.setVisible(true);
            btMotivo.setVisible(true);
            rBloqueado.setSelected(true);
            switch (usuarioSel.getMotivo()) {
                case 1:
                    btMotivo.setValue(MotivoType.FALTA.getDescription());
                    break;
                case 2:
                    btMotivo.setValue(MotivoType.LIVRO_DEFEITO.getDescription());
                    break;
                case 3:
                    btMotivo.setValue(MotivoType.PROBLEMAS.getDescription());
                    break;
            }
        } else {
            textMotivo.setVisible(false);
            btMotivo.setVisible(false);
            if (usuarioSel.getStatus() == 1) {
                rAtivo.setSelected(true);
            } else {
                rInativo.setSelected(true);
            }

        }
        // TIPO DOC
        if (usuarioSel.getTipoDoc() == 1) {
            rCPF.setSelected(true);
        } else if (usuarioSel.getTipoDoc() == 2) {
            rRG.setSelected(true);
        } else {
            rEstudante.setSelected(true);
        }
        // UF
        btUF.setValue(usuarioSel.getUf());
    }

}
