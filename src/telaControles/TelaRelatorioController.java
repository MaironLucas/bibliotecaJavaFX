/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaControles;

import CodigosGerais.MudarCena;
import DAO.EmprestimoDAO;
import entidades.Emprestimo;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author galva
 */
public class TelaRelatorioController implements Initializable {

    @FXML
    private Button btVoltar;
    @FXML
    private BarChart<?, ?> grafico;
    @FXML
    private TableView<Emprestimo> tabela;
    @FXML
    private TableColumn<Emprestimo, String> colLivro;
    @FXML
    private TableColumn<Emprestimo, String> colTempo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colLivro.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getIDLivro().getTitulo()));
        colTempo.setCellValueFactory((param) -> {
            LocalDate dataEmprestimo = param.getValue().getDataEmprestimo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataAtual =  LocalDate.now();
            int daysBetween = (int) Duration.between(dataEmprestimo.atStartOfDay(), dataAtual.atStartOfDay()).toDays();
            return new SimpleStringProperty(Integer.toString(daysBetween));
        });

        Platform.runLater(() -> {
            EmprestimoDAO emprestimoDao = new EmprestimoDAO();
            ObservableList<Emprestimo> listaDeEmprestimos = FXCollections.observableArrayList();
            listaDeEmprestimos.addAll(emprestimoDao.getAllEmprestados());
            tabela.setItems(listaDeEmprestimos);
        });
    }

    @FXML
    private void chamaVoltarInicial(ActionEvent event) {
        BorderPane root = (BorderPane) btVoltar.getScene().getRoot();
        new MudarCena("./telas/TelaInicial.fxml", root);
    }

}
