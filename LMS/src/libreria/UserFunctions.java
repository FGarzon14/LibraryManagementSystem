package libreria;

import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class UserFunctions {
	
	private static ConexionDB conexionDB;

    public UserFunctions() {
    	conexionDB = new ConexionDB();
        // Constructor
    }

    public DefaultListModel<String> getBookList() {
        DefaultListModel<String> bookList = new DefaultListModel<>();
        String query = "SELECT * FROM libros";
        try (Connection connection = conexionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bookId = resultSet.getInt("libro_id");
                String title = resultSet.getString("libro_titulo");
                String author = resultSet.getString("libro_autor");
                int ISBN = resultSet.getInt("ISBN");
                Date publishingDate = resultSet.getDate("fecha_publicacion");
                String publisher = resultSet.getString("editorial");
                String genre = resultSet.getString("libro_genero");

                String bookInfo = "ID: " + bookId + " - Title: " + title + " - Author: " + author + 
                                  " - ISBN: " + ISBN + " - Publishing date: " + publishingDate + 
                                  " - Publishing House: " + publisher + " - Genre: " + genre;
                bookList.addElement(bookInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
    
    
    public DefaultListModel<String> getMyBooksList(int userID) {
    	DefaultListModel<String> myBooksList = new DefaultListModel<>();
    	String query = "SELECT p.id_prestamo, p.libro_id, l.libro_titulo, p.fecha_prestamo, p.fecha_devolucion, "
                + "p.estado_devuelto FROM prestamos p "
                + "JOIN libros l ON p.libro_id = l.libro_id "
                + "WHERE p.usuario_id = ?";

    	try(Connection connection = conexionDB.getConnection()){
    		PreparedStatement statement = connection.prepareStatement(query);
    		statement.setInt(1, userID);
    		ResultSet resultSet = statement.executeQuery();
    		while(resultSet.next()) {
    			int loanID = resultSet.getInt("id_prestamo");
    			int bookID = resultSet.getInt("libro_id");
    			String bookTitle = resultSet.getString("libro_titulo");
    			Date issueDate = resultSet.getDate("fecha_prestamo");
    			Date returnDate = resultSet.getDate("fecha_devolucion");
    			Boolean isReturned = resultSet.getBoolean("estado_devuelto");
    			
    			String loanInfo = "Loan ID: " + loanID + " - Book ID: " + bookID + "- Book title: " + bookTitle + " - Issued on: "
    					 + issueDate + " - Returned on: " + returnDate + "- Returned: " + isReturned;
    			myBooksList.addElement(loanInfo);
    		}
    		
    		
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return myBooksList;
		
    }
    
}
