package libreria;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class AdminReturnedList {

	AdminFunctions adminFunctions;
	public AdminReturnedList() {
		// TODO Auto-generated constructor stub
		adminFunctions = new AdminFunctions();
		
		JFrame issuedListFrame = new JFrame("Return History");
        issuedListFrame.setSize(800, 400);
        issuedListFrame.setLayout(new BorderLayout());

		DefaultListModel<String> loanModel = adminFunctions.getReturnedList();
        JList<String> returnedList = new JList<>(loanModel);
        JScrollPane returnedListScrollPane = new JScrollPane(returnedList);

        issuedListFrame.add(returnedListScrollPane, BorderLayout.CENTER);

        issuedListFrame.setVisible(true);
	}

}
