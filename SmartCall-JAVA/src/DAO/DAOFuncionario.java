/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import smartcall.java.Classes.Funcionario;
import smartcall.java.Database.c_ConexaoDB;

/**
 *
 * @author Raissa do Valle
 */
public class DAOFuncionario {
    
     public List<Funcionario> getList()
     {
        List<Funcionario> listaFuncionario = new ArrayList<>();
        Connection con = c_ConexaoDB.getConnection();
        String sql = "SELECT f.*, s.idSetor, s.nomeSetor from funcionario f join setor s on s.idSetor = f.idSetor;";
        
        try
        {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        
            while(rs.next())
            {
                 Funcionario c = new Funcionario();
                 
                 c.setCpfCnpj(rs.getString("f.cpfCnpj"));  
                 c.setNome(rs.getString("f.nome"));
                 c.setTelefone(rs.getString("f.email"));
                 c.setEmail(rs.getString("f.email"));
                 c.setIdSetor(rs.getInt("f.idSetor"));
                 c.setNomeSetor(rs.getString("f.nomeSetor"));

                 listaFuncionario.add(c);
            }
            stmt.close();
            rs.close();
        }
        catch(SQLException ex)
        {
            System.out.println("Erro, lista não retornada");
            return null;
        }
        return listaFuncionario;               
    }

    public boolean AdicionarFuncionario(Funcionario funcionario) {
        Connection con = c_ConexaoDB.getConnection();
        
        String sql = "INSERT INTO funcionario (cpfCnpj, nome, logradouro, numero, bairro, cidade, estado, cep, email, telefone, idSetor) "
                + "VALUES ('" + funcionario.getCpfCnpj() + "', '" + funcionario.getNome() + "', '" + funcionario.getLogradouro() + "', " + funcionario.getNumero() + ", '" + funcionario.getBairro() + "', '" + funcionario.getCidade() + "', "
                + "'" + funcionario.getEstado() + "', '" + funcionario.getCep() + "', '" + funcionario.getEmail() + "', '" + funcionario.getTelefone() + "', " + funcionario.getIdSetor() + ")";
        
        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex);
            return false;            
        }
    }

    public boolean ExcluirFuncionario(String cpfCnpj) {
                Connection con = c_ConexaoDB.getConnection();
        
        String sql = "DELETE FROM funcionario WHERE cpfCnpj = '" + cpfCnpj + "';";
        
        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex);
            return false;            
        }
    }
    
    public boolean AtualizarFuncionario(Funcionario funcionario, String cpfCnpj) {
        Connection con = c_ConexaoDB.getConnection();

        String sql = "UPDATE funcionario SET cpfCnpj = '" + funcionario.getCpfCnpj() + "', nome = '" + funcionario.getNome() + "', endereco = '" + funcionario.getLogradouro() + "', numero = " + funcionario.getNumero() + ", bairro ='" + funcionario.getBairro() + "', "
        +"cidade = '" + funcionario.getCidade() + "', estado = '" + funcionario.getEstado() + "', cep = '" + funcionario.getCep() + "', email = '" + funcionario.getEmail() + "', telefone = '" + funcionario.getTelefone() + "', idSetor = " + funcionario.getIdSetor()
        +" WHERE cpfCnpj = '" + cpfCnpj + "';";

        try{
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
            return true;
        }
        catch(SQLException ex){
            System.out.println(ex);
            return false;            
        }
    }
    
}