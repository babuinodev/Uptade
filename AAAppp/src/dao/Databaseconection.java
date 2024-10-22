package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaseconection {
	 private static final String URL = "jdbc:mysql://localhost:3306/aps";
	    private static final String USER = "root"; // Usuário padrão do XAMPP
	    private static final String PASSWORD = ""; // Senha padrão (deixe em branco se não houver)

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	    
	}