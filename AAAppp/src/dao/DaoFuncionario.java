package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidade.Funcionario;

public class DaoFuncionario {
	 public void save(Funcionario funcionario) {
	        String sql = "INSERT INTO funcionarios (id, nome, salario) VALUES (?, ?, ?)";

	        try (Connection connection = Databaseconection.getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, funcionario.getid());
	            pstmt.setString(2, funcionario.getnome());
	            pstmt.setDouble(3, funcionario.getsalario());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Funcionario> loadAll() {
	        List<Funcionario> funcionarios = new ArrayList<>();
	        String sql = "SELECT * FROM funcionarios";

	        try (Connection connection = Databaseconection.getConnection();
	                Statement stmt = connection.createStatement();
	                ResultSet rs = stmt.executeQuery(sql)) {
	               while (rs.next()) {
	                   Funcionario funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getDouble("salario"));
	                   funcionarios.add(funcionario);
	               }
	           } catch (SQLException e) {
	               System.err.println("Erro ao executar a consulta: " + e.getMessage());
	               e.printStackTrace();
	           }
	           return funcionarios;
	       }
	    
	    public Funcionario findById(int id) {
	        Funcionario funcionario = null;
	        String sql = "SELECT * FROM funcionarios WHERE id = ?";

	        try (Connection connection = Databaseconection.getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getDouble("salario"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return funcionario;
	    }
	}