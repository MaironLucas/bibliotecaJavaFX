/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.sql.*;

/**
 *
 * @author mairo
 */
public class CriarTabelas {
    private Connection conexao;
    
    public void criarTabela(String sql) throws SQLException{
        if (conexao == null){
            throw new SQLException("Banco nao conectado!");
        }
        Statement sessao = conexao.createStatement(); 
        int resposta = sessao.executeUpdate(sql);
    }
    
    public void conectar() throws SQLException, ClassNotFoundException{
        if (conexao != null)
            throw new SQLException("Conexao já estabelecida!");
        String url = "jdbc:mysql://localhost:3306/loo?serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexao = DriverManager.getConnection(url, "root", "anjtmai9810");
        System.out.println("Conexao realizada"); 
    }
}
