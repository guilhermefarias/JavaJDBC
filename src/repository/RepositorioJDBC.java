package repository;
import exception.ExcecaoBancoDados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class RepositorioJDBC {
	private static RepositorioJDBC minhaInstancia;
	public static RepositorioJDBC instanciar(){
		if(minhaInstancia == null){
			minhaInstancia = new RepositorioJDBC();
		}
		return minhaInstancia;
	}
	
	
	private RepositorioJDBC(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver carregado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void incluir(String nome) throws ExcecaoBancoDados {
		Connection con = null;
		Statement st = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/java?user=root&password=");
			st = con.createStatement();
			String sqlQuery = "INSERT INTO `people` (`name`) VALUES ('" + nome +"')";
			
			int rows = st.executeUpdate(sqlQuery);
		} catch (SQLException e){
			throw new ExcecaoBancoDados("Erro no banco de dados", e);
		} finally {
			try {
				st.close();
			} catch (Exception e){}
			try {
				con.close();
			} catch (Exception e){}
		}
	}
	
	public void remover(int id) throws ExcecaoBancoDados {
		Connection con = null;
		Statement st = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/java?user=root&password=");
			st = con.createStatement();
			String sqlQuery = "DELETE FROM `people` WHERE `id` = "+ id;
			int rows = st.executeUpdate(sqlQuery);
		} catch (SQLException e){
			throw new ExcecaoBancoDados("Erro no banco de dados", e);
		} finally {
			try {
				st.close();
			} catch (Exception e){}
			try {
				con.close();
			} catch (Exception e){}
		}
	}
	
	public void listar() throws ExcecaoBancoDados {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/java?user=root&password=");
			st = con.createStatement();
			String sqlQuery = "SELECT * FROM people";
			rs = st.executeQuery(sqlQuery);
			
			while(rs.next()){
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				
				System.out.println(id + " - " + nome);
			}
		} catch (SQLException e){
			throw new ExcecaoBancoDados("Erro no banco de dados", e);
		} finally {
			try {
				st.close();
			} catch (Exception e){}
			try {
				con.close();
			} catch (Exception e){}
		}
	}
}
