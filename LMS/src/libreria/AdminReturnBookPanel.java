package libreria;

import java.awt.*;
import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class AdminReturnBookPanel {
	
	AdminFunctions adminFunctions;

	public AdminReturnBookPanel() {
		// TODO Auto-generated constructor stub
		adminFunctions = new AdminFunctions();

		JFrame returnBookFrame = new JFrame("Return book");
		returnBookFrame.setSize(500, 250);
		returnBookFrame.setLayout(new GridLayout(4, 2));
		
		JLabel bookIDLabel = new JLabel("Book id");
		TextField bookIDInput = new TextField();
		JLabel userLabel = new JLabel("Username");
		TextField userInput = new TextField();
		JLabel dateLabel = new JLabel("Date");
		JXDatePicker dateInput = new JXDatePicker();
        dateInput.setDate(Calendar.getInstance().getTime());
		JButton returnButton = new JButton("Return book");
		JButton resetButton = new JButton("Reset");
		
		returnBookFrame.add(bookIDLabel);
		returnBookFrame.add(bookIDInput);
		returnBookFrame.add(userLabel);
		returnBookFrame.add(userInput);
		returnBookFrame.add(dateLabel);
		returnBookFrame.add(dateInput);
		returnBookFrame.add(resetButton);
		returnBookFrame.add(returnButton);
		returnBookFrame.add(resetButton);
		
		returnButton.addActionListener(e -> {
			String username = userInput.getText();
			Integer bookID = null;
            try {
                bookID = Integer.parseInt(bookIDInput.getText());
            } catch (NumberFormatException ex) {
                // If ISBN is not a valid number, we ignore it
            }
            Date dateUtil = dateInput.getDate();
            java.sql.Date dateSql = (dateUtil != null) ? new java.sql.Date(dateUtil.getTime()) : null;
                                 
			adminFunctions.returnBook(bookID, username, dateSql);
		});
		
		
		
		
		returnBookFrame.setVisible(true);
	}

}
