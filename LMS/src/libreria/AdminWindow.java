package libreria;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminWindow {

	public AdminWindow() {
		// TODO Auto-generated constructor stub
		JFrame adminWindow = new JFrame();
		adminWindow.setSize(600, 300);
		//Layout to be determined.
		adminWindow.setLayout(new GridLayout(5, 2));
		
		//Acciones del admin: Crear usuario, borrar usuario, añadir libro, eliminar libro, ver lista de libros, prestar libro, devolver libro,
		//ver lista de libros prestados, ver lista de libros devueltos.
		
		//Creamos las acciones con botones para añadir al adminWindow
		
		JButton addUser = new JButton("Add user");
		JButton removeUser= new JButton("Remove user");
		JButton addBook = new JButton("Add book");
		JButton removeBook = new JButton("Remove book");
		JButton issueBook = new JButton("Issue a new book");
		JButton returnBook = new JButton("Return a book");
		JButton userList = new JButton("User list");
		JButton bookList = new JButton("Book list");
		JButton issuedList = new JButton("Issued books list");
		JButton returnedList = new JButton("Returned books list");
		
		adminWindow.add(addUser);
		adminWindow.add(removeUser);
		adminWindow.add(addBook);
		adminWindow.add(removeBook);
		adminWindow.add(issueBook);
		adminWindow.add(returnBook);
		adminWindow.add(userList);
		adminWindow.add(bookList);
		adminWindow.add(issuedList);
		adminWindow.add(returnedList);
		
		adminWindow.setVisible(true);
		
		
		addUser.addActionListener(e -> new AdminAddUserPanel());
		removeUser.addActionListener(e -> new AdminRemoveUserPanel());
		addBook.addActionListener(e -> new AdminAddBookPanel());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminWindow();
		
	}

}
