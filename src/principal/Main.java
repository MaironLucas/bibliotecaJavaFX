/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mairo
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Create the FXMLLoader
        try{
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("./telas/TelaInicial.fxml"));
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gerenciador de Biblioteca");
            primaryStage.show(); 
        } catch (IOException e){
            System.out.println("Carregou tortao");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
