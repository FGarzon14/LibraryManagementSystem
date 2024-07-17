package libreria;

import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class AdminIssueBookPanel {
	
	private AdminFunctions adminFunctions;

	public AdminIssueBookPanel() {
		adminFunctions = new AdminFunctions();
		
		JFrame issueBookFrame = new JFrame("Issue book");
		issueBookFrame.setSize(400, 200);
		issueBookFrame.setLayout(new GridLayout(4, 2));
		
		JLabel bookIDLabel = new JLabel("Book ID:");
		TextField bookIDInput = new TextField();
		JLabel usernameLabel = new JLabel("Borrowed to user:");
		TextField usernameInput = new TextField();
		JLabel dateLabel = new JLabel("Issued on date:");
		JXDatePicker dateInput = new JXDatePicker();
        dateInput.setDate(Calendar.getInstance().getTime());
        
        
		JButton issueButton = new JButton("Issue Book");
		JButton resetButton = new JButton("Reset fields");
		
		issueBookFrame.add(bookIDLabel);
		issueBookFrame.add(bookIDInput);
		issueBookFrame.add(usernameLabel);
		issueBookFrame.add(usernameInput);
		issueBookFrame.add(dateLabel);
		issueBookFrame.add(dateInput);
		issueBookFrame.add(issueButton);
		issueBookFrame.add(resetButton);
		

        resetButton.addActionListener(e -> {
            bookIDInput.setText("");
            usernameInput.setText("");
            dateInput.setDate(null);
        });
        
        issueButton.addActionListener(e -> {
        	Integer bookID = null;
            try {
                bookID = Integer.parseInt(bookIDInput.getText());
            } catch (NumberFormatException ex) {
                // If ISBN is not a valid number, we ignore it
            }
            String username = usernameInput.getText();
            Date dateUtil = dateInput.getDate();
            java.sql.Date dateSql = (dateUtil != null) ? new java.sql.Date(dateUtil.getTime()) : null;

            adminFunctions.issueBook(bookID, username, dateSql);
        });
        
		issueBookFrame.setVisible(true);
	}

}
