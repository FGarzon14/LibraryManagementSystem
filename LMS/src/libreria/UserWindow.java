package libreria;

import java.awt.*;
import javax.swing.*;

public class UserWindow {

    UserFunctions userFunctions;
    private int userID;

    public UserWindow(int userID) {
    	this.userID = userID;
        userFunctions = new UserFunctions();
        

        // Configurar la ventana principal
        JFrame userWindow = new JFrame("User Dashboard");
        userWindow.setSize(400, 200);
        userWindow.setLayout(new GridLayout(2, 2));
     // Hacer visible la ventana principal
     

        // Crear botones
        JButton viewBooks = new JButton("View books");
        JButton viewUserBooks = new JButton("My books");

        // Añadir botones a la ventana principal
        userWindow.add(viewBooks);
        userWindow.add(viewUserBooks);
        
        userWindow.setVisible(true);

        // Añadir ActionListener al botón "View books"
        viewBooks.addActionListener(e -> showBookList());
        viewUserBooks.addActionListener(e -> showMyBooksList());

        
    }

   
	private void showBookList() {
        // Obtener la lista de libros
        DefaultListModel<String> bookListModel = userFunctions.getBookList();
        JList<String> bookList = new JList<>(bookListModel);
        JScrollPane scrollPane = new JScrollPane(bookList);

        // Crear una nueva ventana para mostrar la lista de libros
        JFrame bookListFrame = new JFrame("Book List");
        bookListFrame.setSize(600, 400);
        bookListFrame.setLayout(new BorderLayout());
        bookListFrame.add(scrollPane, BorderLayout.CENTER);
        
        

        // Hacer visible la nueva ventana
        bookListFrame.setVisible(true);
    }
	
	private void showMyBooksList() {
        // Obtener la lista de libros
        DefaultListModel<String> bookListModel = userFunctions.getMyBooksList(userID);
        JList<String> bookList = new JList<>(bookListModel);
        JScrollPane scrollPane = new JScrollPane(bookList);

        // Crear una nueva ventana para mostrar la lista de libros
        JFrame bookListFrame = new JFrame("Book List");
        bookListFrame.setSize(600, 400);
        bookListFrame.setLayout(new BorderLayout());
        bookListFrame.add(scrollPane, BorderLayout.CENTER);
        
        

        // Hacer visible la nueva ventana
        bookListFrame.setVisible(true);
    }
	
	
}
