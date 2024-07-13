package libreria;
import java.awt.*;
import javax.swing.*;


//Esta clase creará los paneles de añadir/eliminar usuarios y libros, así como registrar préstamos y devoluciones.
public class AdminAddUser {
	
	private AdminFunctions adminFunctions;

	public AdminAddUser() {
		
		adminFunctions = new AdminFunctions();
		
		//
		//Creación del panel addUser desde AdminWindow7
		//
		
		JFrame addUserFrame = new JFrame("addUser");
		addUserFrame.setSize(500, 250);
		addUserFrame.setLayout(new GridLayout(4, 2));
		
		JLabel usernameLabel = new JLabel("Username");
		TextField usernameInput = new TextField();
		JLabel passwordLabel = new JLabel("Password");
		JPasswordField passwordInputAddUser = new JPasswordField();
		JLabel isAdminLabel = new JLabel("Is the new user an admin?");
		
		//Frame interno para añadir un checkbox que nos de la opción de admin para el usuario
		JPanel adminConfirmationPanel = new JPanel();
		JRadioButton noAdmin = new JRadioButton("No");
		JRadioButton yesAdmin = new JRadioButton("Yes");
		
		//Añadimos los botones al adminConfirmationFrame
		adminConfirmationPanel.add(noAdmin);
		adminConfirmationPanel.add(yesAdmin);
		
		//Creamos un grupo de botones para que sean excluyentes noAdmin y siAdmin
		ButtonGroup isAdminGroup = new ButtonGroup();
		isAdminGroup.add(noAdmin);
		isAdminGroup.add(yesAdmin);
		
		//Creación de los botones para añadir o reiniciar los campos
		JButton addUserButton = new JButton("Add user");
		JButton resetButtonAddUser = new JButton("Reset");
		
		//Añadimos los elementos al frame
		addUserFrame.add(usernameLabel);
		addUserFrame.add(usernameInput);
		addUserFrame.add(passwordLabel);
		addUserFrame.add(passwordInputAddUser);
		addUserFrame.add(isAdminLabel);
		addUserFrame.add(adminConfirmationPanel	);
		addUserFrame.add(addUserButton);
		addUserFrame.add(resetButtonAddUser);
		
		
		//añadimos funcionalidad a los botones del panel Add user
		 resetButtonAddUser.addActionListener(e -> resetFields(usernameInput, passwordInputAddUser, isAdminGroup));
		 //Falta añadir la funcionalidad desde la clase AdminFunctions
		 addUserButton.addActionListener(e -> {
			 boolean isAdmin = yesAdmin.isSelected();
			 adminFunctions.addUser(usernameInput, passwordInputAddUser, isAdmin);
			 addUserFrame.dispose();
		 });
		 
		 addUserFrame.setVisible(true);
		 
		 
		 //
		 //Creación del panel removeUser desde AdminWindow
		//
	}
	
	
	public static void resetFields(TextField textField, JPasswordField passwordField, ButtonGroup isAdminGroup) {
    	textField.setText("");
    	passwordField.setText("");
    	isAdminGroup.clearSelection();
    }

}
