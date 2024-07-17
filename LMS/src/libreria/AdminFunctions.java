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
		
		
		/*
		 * Función para eliminar un libro de la DB
		 */
		
		public void removeBook(String title, String author, Integer ISBN, java.sql.Date publishingDate
				, String publishingHouse, String genre) {
			StringBuilder query = new StringBuilder("DELETE FROM libros WHERE 1 = 1");
			
			if(title != null && !title.isEmpty()) {
				query.append(" AND libro_titulo LIKE ?");
			}
			if(author != null && !author.isEmpty()) {
				query.append(" AND libro_autor LIKE ?");
			}
			if(ISBN != null) {
				query.append(" AND ISBN LIKE ?");
			}
			if(publishingDate != null) {
				query.append(" AND fecha_publicacion LIKE ?");
			}
			if(publishingHouse != null && !publishingHouse.isEmpty()) {
				query.append(" AND editorial LIKE ?");
			}
			if(genre != null && !genre.isEmpty()) {
				query.append(" AND libro_genero LIKE ?");
			}
			
			try (Connection connection = conexionDB.getConnection();
				     PreparedStatement statement = connection.prepareStatement(query.toString())) {

				    int paramIndex = 1;
				    
				    if (title != null && !title.isEmpty()) {
				        statement.setString(paramIndex++, "%" + title + "%");
				    }
				    if (author != null && !author.isEmpty()) {
				        statement.setString(paramIndex++, "%" + author + "%");
				    }
				    if (ISBN != null) {
				        statement.setString(paramIndex++, "%" + ISBN + "%");
				    }
				    if (publishingDate != null) {
				        statement.setString(paramIndex++, "%" + publishingDate + "%");
				    }
				    if (publishingHouse != null && !publishingHouse.isEmpty()) {
				        statement.setString(paramIndex++, "%" + publishingHouse + "%");
				    }
				    if (genre != null && !genre.isEmpty()) {
				        statement.setString(paramIndex++, "%" + genre + "%");
				    }
				    
				    int rowsAffected = statement.executeUpdate();
				    if(rowsAffected > 0) {
				    	JOptionPane.showMessageDialog(null, "Book(s) succesfully removed.");
				    } else {
				    	JOptionPane.showMessageDialog(null, "No books with the specified dates were found.");
				    }
				    
				    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error adding the book.");
			}
			
			
			
		}
		
		/*
		 * Función para añadir un libro a la lista de prestamos
		 */
		public void issueBook(int bookID, String username, java.sql.Date issueDate) {
			String checkQuery = "SELECT COUNT(*) FROM prestamos WHERE libro_ID = ? AND estado_devuelto = FALSE";
			String getUserIDQuery = "SELECT usuario_id FROM usuarios WHERE usuario_nombre = ?";
			String issueBookQuery = "INSERT INTO prestamos (usuario_id, libro_id, fecha_prestamo, estado_devuelto) VALUES (?, ?, ?, FALSE)";
			try(Connection connection = conexionDB.getConnection()){
				
				try(PreparedStatement checkStatement = connection.prepareStatement(checkQuery)){
				checkStatement.setInt(1, bookID);
				ResultSet resultSet = checkStatement.executeQuery();
				if(resultSet.next() && resultSet.getInt(1) > 0) {
					JOptionPane.showMessageDialog(null, "This book is already issued to someone else.");
					return;
				}
				//Creamos la variable para acceder a ella desde fuera del bloque try
				int userID = -1;
				try(PreparedStatement getUserIdStatement = connection.prepareStatement(getUserIDQuery)){
					getUserIdStatement.setString(1, username);
					ResultSet userIDResultSet = getUserIdStatement.executeQuery();
					
					if(userIDResultSet.next()) {
						userID = userIDResultSet.getInt("usuario_id");
					} else {
						JOptionPane.showMessageDialog(null, "The user could not be found");
						return;
					}
				}
				
				try(PreparedStatement issueBookStatement = connection.prepareStatement(issueBookQuery)){
					issueBookStatement.setInt(1, userID);
					issueBookStatement.setInt(2, bookID);
					issueBookStatement.setDate(3, issueDate);
					
					issueBookStatement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book issued correctly");
					
				}
					
				}
				
				
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error issuing the book.");
					
			
			}
		}
		
		
		

		    public static void main(String[] args) {
		        // TODO Auto-generated method stub
		    }
		}