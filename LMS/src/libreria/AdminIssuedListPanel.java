package libreria;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class AdminIssuedListPanel {

	AdminFunctions adminFunctions;
	public AdminIssuedListPanel() {
		adminFunctions = new AdminFunctions();
		

        JFrame issuedListFrame = new JFrame("Loan History");
        issuedListFrame.setSize(800, 400);
        issuedListFrame.setLayout(new BorderLayout());

		DefaultListModel<String> loanModel = adminFunctions.getIssuedList();
        JList<String> loanList = new JList<>(loanModel);
        JScrollPane loanListScrollPane = new JScrollPane(loanList);

        issuedListFrame.add(loanListScrollPane, BorderLayout.CENTER);

        issuedListFrame.setVisible(true);
    }
}


