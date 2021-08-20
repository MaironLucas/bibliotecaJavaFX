/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.CaixaDeAlerta;
import CodigosGerais.Navegar;
import DAO.LivroDAO;
import entidades.Livro;
import exceptions.ExceptionGenerica;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mairo
 */
public class TelaCadastroLivroController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField inputTitulo;
    @FXML
    private TextField inputAutores;
    @FXML
    private TextField inputISBN;
    @FXML
    private TextField inputQtd;
    @FXML
    private TextField inputEndCapa;
    @FXML
    private ImageView imageView;
    
    private String caminho;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chamarTelaInicial(ActionEvent event) {
        Navegar temp = new Navegar("./telas/TelaInicial.fxml", (Stage) btVoltar.getScene().getWindow());
    }

    @FXML
    private void buscarImagem(ActionEvent event) {
        try{
            FileChooser arquivo = new FileChooser();
            arquivo.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
            File temp = arquivo.showOpenDialog((Stage) btnBuscar.getScene().getWindow());
            caminho = temp.toURI().toString();
            System.out.println(caminho);
            Image imagem = new Image(caminho);
            imageView.setImage(imagem);
            inputEndCapa.setText(caminho);
        } catch(Exception e){
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Erro de carregamento", "Falha ao carregar a imagem!");
        }
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        inputTitulo.clear();
        inputAutores.clear();
        inputISBN.clear();
        inputQtd.clear();
    }

    @FXML
    private void salvarLivro(ActionEvent event) {
        Livro livro = new Livro();
        boolean validador = false;
        try{
            livro.setTitulo(inputTitulo.getText());
            livro.setAutores(inputAutores.getText());
            livro.setIsbn(inputISBN.getText());
            livro.setQtdexemplares(Integer.parseInt(inputQtd.getText()));
            livro.setQtdemprestados(0);
            livro.setCapa(caminho);
            validador = true;
        } catch (NumberFormatException e){
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Erro de tipo de dado", "Campo quantidade só aceita números!");
        } catch (ExceptionGenerica e){
            new CaixaDeAlerta(Alert.AlertType.ERROR, "Falha no input", e.getMessage());
        }
        
        if (validador){
            try{
                LivroDAO livroDao = new LivroDAO();
                livroDao.add(livro);
            } catch(Exception e){
                new CaixaDeAlerta(Alert.AlertType.ERROR, "Falha no banco", e.getMessage());
            }
        }
    }
    
}
