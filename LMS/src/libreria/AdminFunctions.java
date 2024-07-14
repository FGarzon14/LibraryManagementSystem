package libreria;

import java.awt.*;
import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

import java.sql.*;


public class AdminFunctions {
	
	private static ConexionDB conexionDB;

	public AdminFunctions() {
		conexionDB = new ConexionDB();
		
		
	}
	//
	//Ecuacion para añadir usuario desde el panel AdminWindow
	//
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

		
		//
		//Function used to remove an user
		//
		public void removeUser(TextField textField) {
			String username = textField.getText();
			
			// Mostrar un mensaje de confirmación al usuario
	        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove user '" + username + "'?", "Confirmation", JOptionPane.YES_NO_OPTION);
	        
	        if(option == JOptionPane.YES_OPTION) {
			
		     try (Connection connection = conexionDB.getConnection()) {
		            String query = "DELETE FROM usuarios WHERE usuario_nombre = ?";
		            try (PreparedStatement statement = connection.prepareStatement(query)) {
		                statement.setString(1, username);
		                int rowsAffected = statement.executeUpdate();
		                
		                System.out.println("Rows affected: " + rowsAffected);  // Debugging

		                if (rowsAffected > 0) {
		                    JOptionPane.showMessageDialog(null, "User successfully removed");
		                } else {
		                    JOptionPane.showMessageDialog(null, "No user found with that name");
		                }
		            }
		        } catch (SQLException sqlException) {
		            sqlException.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error removing the user");
		        }
		    }else {
		    	JOptionPane.showMessageDialog(null, "Removal of the user canceled.");
		} 
			
		}
		
		//
		//Ecuación para añadir un libro a la base de datos
		//
		public void addBook(TextField text1, TextField text2, TextField text3, TextField text4,
	    		TextField text5, JXDatePicker datePicker) {
			String title = text1.getText();
			String author = text2.getText();
			String ISBN = text3.getText();
			String publishingHouse = text4.getText();
			String genre = text5.getText();
			java.util.Date publishingDateUtil = datePicker.getDate();
		    java.sql.Date publishingDateSql = new java.sql.Date(publishingDateUtil.getTime());

			try(Connection connection = conexionDB.getConnection()){
				String query = "INSERT INTO libros (libro_titulo"
						+ ", libro_autor, ISBN, fecha_publicacion, editorial,"
						+ " libro_genero) VALUES (?, ?, ?, ? , ?, ?)";
				try(PreparedStatement statement = connection.prepareStatement(query)){
					statement.setString(1, title);
					statement.setString(2, author);
					statement.setString(3, ISBN);
					statement.setDate(4, publishingDateSql);
					statement.setString(5, publishingHouse);
					statement.setString(6, genre);
					
					int rowsAffected = statement.executeUpdate();
					
					if(rowsAffected > 0) {
						JOptionPane.showMessageDialog(null, "Book added to the database.");
						
					} else {
						JOptionPane.showMessageDialog(null, "Book could not be added to the database.");
					}
				}
				
			} catch(SQLException sqlException) {
				sqlException.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error adding the book");
			}
			}
		
		

		    public static void main(String[] args) {
		        // TODO Auto-generated method stub
		    }
		}