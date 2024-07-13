package libreria;

import java.awt.*;
import javax.swing.*;
import java.sql.*;


public class AdminFunctions {
	
	private static ConexionDB conexionDB;

	public AdminFunctions() {
		conexionDB = new ConexionDB();
		
		
	}
	
	//Ecuacion para añadir usuario desde el panel AdminWindow
		public void addUser(TextField textField, JPasswordField passwordField, boolean isAdmin) {
		//Asignamos las variables
		String username = textField.getText();
		String password = new String(passwordField.getPassword());	
		// Si el boolean es true, admin será 1.
		int admin = isAdmin ? 1 : 0;
		
		
		
		try(Connection connection = conexionDB.getConnection()){
			//Creamos la query para añadir el usuario
			String query = "INSERT INTO USUARIOS (usuario_nombre, usuario_password, usuario_tipo) VALUES (?, ?, ?)";
			try(PreparedStatement statement = connection.prepareStatement(query)){
				statement.setString(1, username);
				statement.setString(2, password);
				statement.setInt(3, admin);
				statement.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "User succesfully added");
			}
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error adding the user");
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
