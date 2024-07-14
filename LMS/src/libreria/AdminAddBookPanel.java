package libreria;

import java.awt.*;
import java.util.Calendar;
import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;

public class AdminAddBookPanel {

    private AdminFunctions adminFunctions;

    public AdminAddBookPanel() {
        adminFunctions = new AdminFunctions();

        JFrame addBookFrame = new JFrame("Add Book");
        addBookFrame.setSize(500, 250);
        addBookFrame.setLayout(new GridLayout(7, 2));

        JLabel titleLabel = new JLabel("Title");
        TextField titleInput = new TextField();
        JLabel authorLabel = new JLabel("Author");
        TextField authorInput = new TextField();
        JLabel ISBNLabel = new JLabel("ISBN");
        TextField ISBNInput = new TextField();
        JLabel publishedLabel = new JLabel("Date of publish (DD/MM/YYYY)");

        JXDatePicker publishedDate = new JXDatePicker();
        publishedDate.setDate(Calendar.getInstance().getTime());

        JLabel publishingHouseLabel = new JLabel("Published by");
        TextField publishingHouseInput = new TextField();
        JLabel genreLabel = new JLabel("Genre");
        TextField genreInput = new TextField();

        JButton addBookButton = new JButton("Add book");
        JButton resetButton = new JButton("Reset field");

        addBookFrame.add(titleLabel);
        addBookFrame.add(titleInput);
        addBookFrame.add(authorLabel);
        addBookFrame.add(authorInput);
        addBookFrame.add(ISBNLabel);
        addBookFrame.add(ISBNInput);
        addBookFrame.add(publishedLabel);
        addBookFrame.add(publishedDate);
        addBookFrame.add(publishingHouseLabel);
        addBookFrame.add(publishingHouseInput);
        addBookFrame.add(genreLabel);
        addBookFrame.add(genreInput);

        addBookFrame.add(addBookButton);
        addBookFrame.add(resetButton);

        resetButton.addActionListener(e -> reset(titleInput, authorInput, ISBNInput, publishingHouseInput, genreInput, publishedDate));
        addBookButton.addActionListener(e ->{
        	adminFunctions.addBook(titleInput,
            		authorInput, ISBNInput, publishingHouseInput, genreInput, publishedDate);
        	addBookFrame.dispose();
        });
        
        		
        
        
        addBookFrame.setVisible(true);
    }
    
    
    public void reset(TextField text1, TextField text2, TextField text3, TextField text4,
    		TextField text5, JXDatePicker date) {
    	text1.setText("");
    	text2.setText("");
    	text3.setText("");
    	text4.setText("");
    	text5.setText("");
    	date.setDate(null);
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminAddBookPanel();
		
	}
}

