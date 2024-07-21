package libreria;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class AdminBookListPanel {
	AdminFunctions adminFunctions;

	public AdminBookListPanel() {
		// TODO Auto-generated constructor stub
		 adminFunctions = new AdminFunctions();

			// TODO Auto-generated constructor stub
			JFrame bookListFrame = new JFrame("Userlist");
			bookListFrame.setSize(800, 400);
			bookListFrame.setLayout(new BorderLayout());
			
			
			
			DefaultListModel<String> bookModel = adminFunctions.getBookList();
			JList<String> bookList = new JList<>(bookModel);
			JScrollPane bookListScrollPane = new JScrollPane(bookList);
			
			bookListFrame.add(bookListScrollPane);
			
		    // Añadir componentes al JFrame
	        bookListFrame.add(bookListScrollPane, BorderLayout.CENTER);

			
			
			bookListFrame.setVisible(true);
	}

}
