package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidade.Carro;

public class DaoCarroBD implements DaoCarro {
	public void save(Carro carro) {
        String sql = "INSERT INTO carros (id, nome, modelo, ano, preco) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Databaseconection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, carro.getid());
            pstmt.setString(2, carro.getNomedocarro());
            pstmt.setString(3, carro.getModelo());
            pstmt.setInt(4, carro.getAno());
            pstmt.setDouble(5, carro.getPreco());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Carro> loadAll() {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM carros";

        try (Connection connection = Databaseconection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Carro carro = new Carro(rs.getInt("id"), rs.getString("nome"), rs.getString("modelo"), rs.getInt("ano"), rs.getDouble("preco"));
                carros.add(carro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carros;
    }
}