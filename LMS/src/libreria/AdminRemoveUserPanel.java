package libreria;

import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

//Con esta clase creamos los paneles de eliminar usuarios
public class AdminRemoveUserPanel {
	
	private AdminFunctions adminFunctions;

	public AdminRemoveUserPanel() {
		adminFunctions = new AdminFunctions();
		
		
		JFrame removeUserFrame = new JFrame("Remove User");
		removeUserFrame.setSize(300, 150);
		removeUserFrame.setLayout(new GridLayout(2, 2));
		removeUserFrame.setVisible(true);
		
		
		JLabel usernameLabel = new JLabel("Username");
		TextField usernameInput = new TextField();
		
		
		//Creación de los botones para añadir o reiniciar los campos
		JButton removeUserButton = new JButton("Remove user");
		JButton resetButton = new JButton("Reset");
		
		//Añadimos los elementos al frame
		removeUserFrame.add(usernameLabel);
		removeUserFrame.add(usernameInput);
		removeUserFrame.add(removeUserButton);
		removeUserFrame.add(resetButton);
		
		//Añadimos funcionalidad a los botones
		resetButton.addActionListener(e -> usernameInput.setText(""));
		removeUserButton.addActionListener(e -> {
			adminFunctions.removeUser(usernameInput);
			removeUserFrame.dispose();
		});
		
	}



}
