/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senactech.MCadastroPessoa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.senactech.MCadastroPessoa.model.Pessoa;
import br.com.senactech.MCadastroPessoa.model.Carro;
import br.com.senactech.MCadastroPessoa.conexao.Conexao;

/**
 *
 * @author jairb
 */
public class CarroDAO {

    public void cadastrarCarro(Carro cVO) throws SQLException {
        //Busca conexão com o BD
        Connection con = Conexao.getConexao();
        //cria um objeto stat responsavel por enviar os comandos sql do Java
        //para serem executados dentro do BD
        Statement stat = con.createStatement();
        try {
            //sql vai receber o comando SQL
            String sql;
            sql = "insert into carro values (null,'" + cVO.getPlaca() + "','"
                    + cVO.getMarca() + "','" + cVO.getModelo() + "',"
                    + cVO.getAnoFabricacao() + "," + cVO.getAnoModelo() + ",'"
                    + cVO.getCor() + "', " + cVO.getnPortas() + ", " + cVO.getIdPessoa() + ")";
            //vamos executar o comando construido na string SQL
            stat.execute(sql);
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir Carro!\n" + e.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }

    public ArrayList<Carro> buscarCarros() throws SQLException {
        //Busca conexão com o BD
        Connection con = Conexao.getConexao();
        Statement stat = con.createStatement();
        try {
            String sql;
            sql = "select * from carro";
            //atribuo ao rs o resultado da execução da query no banco
            ResultSet rs = stat.executeQuery(sql);
            ArrayList<Carro> carros = new ArrayList<>();
            while (rs.next()) {
                Carro c = new Carro();
                //lado do java |x| lado do banco
                c.setIdCarro(rs.getInt("idCarro"));
                c.setPlaca(rs.getString("placa"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setAnoFabricacao(rs.getInt("anoFabricacao"));
                c.setAnoModelo(rs.getInt("anoModelo"));
                c.setCor(rs.getString("cor"));
                c.setnPortas(rs.getInt("nPortas"));
                c.setIdPessoa(rs.getInt("idPessoa"));
                carros.add(c);
            }
            return carros;
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar Carros!\n" + e.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }

    public boolean verPlaca(String placa) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        //Cria um objeto stat responsavel por enviar os comandos sql do Java para serem executados dentro do BD
        Statement stat = con.createStatement();
        boolean verPlaca = false;
        try {
            String sql;
            sql = "select placa from carro where placa = '" + placa + "'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                verPlaca = rs.wasNull();
            }
        } catch (SQLException e) {
            throw new SQLException("Carro com esta placa não existe. \n" 
                    + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
        return verPlaca;
    }

    public Carro getByDocBD(String placa) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        //Cria um objeto stat responsavel por enviar os comandos sql do Java para serem executados dentro do BD
        Statement stat = con.createStatement();
        Carro c = new Carro();
        try {
            String sql;
            sql = "select * from carro where placa = '" + placa + "'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                // lado do java |x| lado do banco
                c.setIdCarro(rs.getInt("idCarro"));
                c.setPlaca(rs.getString("placa"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setAnoFabricacao(rs.getInt("anoFabricacao"));
                c.setAnoModelo(rs.getInt("anoModelo"));
                c.setCor(rs.getString("cor"));
                c.setnPortas(rs.getInt("nPortas"));
                c.setIdPessoa(rs.getInt("idPessoa"));
            }
        } catch (SQLException e) {
            throw new SQLException("Carro com esta placa não existe. \n" + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
        return c;
    }

    public void deletarCarro(int id) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        //Cria um objeto stat responsavel por enviar os comandos sql do Java para serem executados dentro do BD
        Statement stat = con.createStatement();
        try {
            String sql;
            sql = "delete from carro where idCarro = " + id;
            stat.execute(sql);
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar carro. \n" + e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }

    public void atualizarCarro (Carro cVO) throws SQLException {
        // Busca conexão com o BD
        Connection con = Conexao.getConexao();
        //Cria um objeto stat responsavel por enviar os comandos sql do Java para serem executados dentro do BD
        Statement stat = con.createStatement();
        try {
            String sql;
            sql = "update carro set " + "placa = '" + cVO.getPlaca()+ "', "
                    + "marca = '" + cVO.getMarca()+ "', " 
                    + "modelo = '" +cVO.getModelo()+ "', " 
                    + "anoFabricacao = " + cVO.getAnoFabricacao()+ ", "
                    + "anoModelo = " + cVO.getAnoModelo()+ ", "
                    + "cor = '" + cVO.getCor()+ "', " 
                    + "nPortas = " + cVO.getnPortas()+ ", "
                    + "idPessoa = " + cVO.getIdPessoa() + " "
                    + "where idCarro = " + cVO.getIdCarro() + "; ";
            stat.executeUpdate (sql);
        } catch (Exception ex) {
            throw new SQLException("Erro ao atualizar carro. \n" + ex.getMessage());
        }finally{
            con.close();
            stat.close();
        }
    }
}
