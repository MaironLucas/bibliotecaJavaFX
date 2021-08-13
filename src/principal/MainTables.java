/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
/**
 *
 * @author mairo
 */


public class MainTables {
    public static void main(String[] args){
        CriarTabelas criarTabela = new CriarTabelas();
        try{
            criarTabela.conectar();
            InputStream arquivo = MainTables.class.getResourceAsStream("/entidades/sql.txt");
            String buffer = "";
            int inputChar;
            while((inputChar = arquivo.read()) != -1){
                buffer += (char) inputChar;
                if(';' == (char) inputChar){
                    System.out.println(buffer);
                    criarTabela.criarTabela(buffer);
                    System.out.println("Tabela criada com sucesso");
                    buffer = "";
                }
            }
            
        } catch(SQLException | ClassNotFoundException e){
            System.out.println("Problema com o banco: " + e);
        } catch (IOException | NullPointerException ex) {
            System.out.println("Problema com arquivo: " + ex);
        }
    }
}
