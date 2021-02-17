package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import db.DB;
import db.DBException;

public class Program {

	public static void main(String[] args) {

		recuperaRegistros();
		insereVariosRegistros(new String[] {"P&D","Desenv"});
//		atualizaRegistros(6, "Desenv.");
//		removeRegistros(6);
		recuperaRegistros();
	}

	private static void recuperaRegistros() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();

			System.out.println("Conectado com sucesso no banco!");
			
			String sql = "SELECT * FROM DEPARTMENT";

			st = conn.createStatement();
			
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("Deparment: " + rs.getInt("Id") + " - " + rs.getString("Name"));
			}
			

		} catch (Throwable t) {
			t.printStackTrace();
			throw new DBException(t.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
			System.out.println("Conexão fechada com sucesso!");
		}
	}
	
	private static void insereRegistros(String name) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();

			System.out.println("Conectado com sucesso no banco!");
			
			String sql = "INSERT INTO DEPARTMENT (NAME) VALUES (?)";

			st = conn.prepareStatement(sql);
			
			st.setString(1, name);
			
			st.executeUpdate();
			
		} catch (Throwable t) {
			t.printStackTrace();
			throw new DBException(t.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
			System.out.println("Conexão fechada com sucesso!");
		}
	}
	
	private static void insereVariosRegistros(String[] names) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();

			System.out.println("Conectado com sucesso no banco!");
			
			String sql = "INSERT INTO DEPARTMENT (NAME) VALUES ('" + names[0] + "'),('" + names[1] + "')";

			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				while (rs.next()) {
					System.out.println("ID: " + rs.getInt(1));
				}
				
			}
			
		} catch (Throwable t) {
			t.printStackTrace();
			throw new DBException(t.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
			System.out.println("Conexão fechada com sucesso!");
		}
	}

	
	private static void atualizaRegistros(int id, String name) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();

			System.out.println("Conectado com sucesso no banco!");
			
			String sql = "UPDATE DEPARTMENT SET NAME = ? WHERE ID = ?";

			st = conn.prepareStatement(sql);
			
			st.setString(1, name);
			st.setInt(2, id);
			
			st.executeUpdate();
			
		} catch (Throwable t) {
			t.printStackTrace();
			throw new DBException(t.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
			System.out.println("Conexão fechada com sucesso!");
		}
	}
	
	private static void removeRegistros(int id) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();

			System.out.println("Conectado com sucesso no banco!");
			
			String sql = "DELETE FROM DEPARTMENT WHERE ID = ?";

			st = conn.prepareStatement(sql);
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		} catch (Throwable t) {
			t.printStackTrace();
			throw new DBException(t.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
			System.out.println("Conexão fechada com sucesso!");
		}
	}

}
