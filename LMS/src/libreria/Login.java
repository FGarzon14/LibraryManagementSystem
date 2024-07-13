package libreria;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Login {
	
	private static ConexionDB conexionDB;
	private static JFrame loginFrame;

	public Login() {
		//inicializamos la conexion a la BD
		conexionDB = new ConexionDB();
		
		//Create the frame, set size and layout.
		loginFrame = new JFrame("login");
		loginFrame.setSize(300, 150);
		loginFrame.setLayout(new GridLayout(3, 2));
		
		
		//Create labels and inputs for username and password
		JLabel usernameLabel = new JLabel("Username");
		TextField usernameInput = new TextField();
		JLabel passwordLabel = new JLabel("Password");
		JPasswordField passwordInput = new JPasswordField();
		
		//Login button and reset button
		JButton loginButton = new JButton("Login");
		JButton resetButton = new JButton("Reset");
		
		loginFrame.add(usernameLabel);
		loginFrame.add(usernameInput);
		loginFrame.add(passwordLabel);
		loginFrame.add(passwordInput);
		loginFrame.add(loginButton);
		loginFrame.add(resetButton);
	    loginFrame.setVisible(true);
	    
	    
	    resetButton.addActionListener(e -> resetFields(usernameInput, passwordInput));
	    loginButton.addActionListener(e -> login(usernameInput, passwordInput));
	    
	    }
	
		public static void resetFields(TextField textField, JPasswordField passwordField) {
	    	textField.setText("");
	    	passwordField.setText("");
	    }
		//Metodo login
		public static void login(TextField textField, JPasswordField passwordField) {
			//Asignamos las variables usuario y contraseña
			String username = textField.getText();
			String password = new String(passwordField.getPassword());
			
			//Creamos la conexion con un bloque try/catch. 
			try(Connection connection = conexionDB.getConnection()){
				//Establecemos la query
				String query = "SELECT * FROM usuarios WHERE usuario_nombre=? AND"
						+ " usuario_password = ?";
				try(PreparedStatement statement = connection.prepareStatement(query)){	
					statement.setString(1, username);
					statement.setString(2, password);
					
					try(ResultSet resultSet = statement.executeQuery()){
						if(resultSet.next()) {
							//Login conseguido
							JOptionPane.showMessageDialog(null, "Login succesful!");
							loginFrame.dispose();
							new AdminWindow();
						} else {
							JOptionPane.showMessageDialog(null, "Invalid username or password");
						}
					}
				}
				
				
			} catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
		}

	    public static void main(String[] args) {
	        new Login();
	    }
	}