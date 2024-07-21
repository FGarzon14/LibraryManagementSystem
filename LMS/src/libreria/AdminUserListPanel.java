package libreria;
import java.awt.*;
import javax.swing.*;


public class AdminUserListPanel {
	
    private AdminFunctions adminFunctions;


	public AdminUserListPanel() {
        adminFunctions = new AdminFunctions();

		// TODO Auto-generated constructor stub
		JFrame userListFrame = new JFrame("Userlist");
		userListFrame.setSize(800, 400);
		userListFrame.setLayout(new BorderLayout());
		
		
		
		DefaultListModel<String> userModel = adminFunctions.getUserList();
		JList<String> userList = new JList<>(userModel);
		JScrollPane userListScrollPane = new JScrollPane(userList);
		
		userListFrame.add(userListScrollPane);
		
	    // Añadir componentes al JFrame
        userListFrame.add(userListScrollPane, BorderLayout.CENTER);

		
		
		userListFrame.setVisible(true);
	}

}
