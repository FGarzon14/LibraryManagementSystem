package libreria;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXDatePicker;

public class AdminRemoveBookPanel {
    private AdminFunctions adminFunctions;

    public AdminRemoveBookPanel() {
        adminFunctions = new AdminFunctions();

        JFrame removeBookFrame = new JFrame("Remove Books");
        removeBookFrame.setSize(400, 300);
        removeBookFrame.setLayout(new GridLayout(7, 2));
       	

        JLabel titleLabel = new JLabel("Title");
        TextField titleInput = new TextField();
        JLabel authorLabel = new JLabel("Author");
        TextField authorInput = new TextField();
        JLabel isbnLabel = new JLabel("ISBN");
        TextField isbnInput = new TextField();
        JLabel publishingHouseLabel = new JLabel("Publishing House");
        TextField publishingHouseInput = new TextField();
        JLabel genreLabel = new JLabel("Genre");
        TextField genreInput = new TextField();
        JLabel publishingDateLabel = new JLabel("Publishing Date");
        JXDatePicker publishingDateInput = new JXDatePicker();

        JButton removeButton = new JButton("Remove Books");
        JButton resetButton = new JButton("Reset fields");

        removeBookFrame.add(titleLabel);
        removeBookFrame.add(titleInput);
        removeBookFrame.add(authorLabel);
        removeBookFrame.add(authorInput);
        removeBookFrame.add(isbnLabel);
        removeBookFrame.add(isbnInput);
        removeBookFrame.add(publishingHouseLabel);
        removeBookFrame.add(publishingHouseInput);
        removeBookFrame.add(genreLabel);
        removeBookFrame.add(genreInput);
        removeBookFrame.add(publishingDateLabel);
        removeBookFrame.add(publishingDateInput);
        removeBookFrame.add(removeButton);
        removeBookFrame.add(resetButton);
        
        removeButton.addActionListener(e -> {
            String title = titleInput.getText();
            String author = authorInput.getText();
            Integer ISBN = null;
            try {
                ISBN = Integer.parseInt(isbnInput.getText());
            } catch (NumberFormatException ex) {
                // If ISBN is not a valid number, we ignore it
            }
            String publishingHouse = publishingHouseInput.getText();
            String genre = genreInput.getText();
            Date publishingDateUtil = publishingDateInput.getDate();
            java.sql.Date publishingDateSql = (publishingDateUtil != null) ? new java.sql.Date(publishingDateUtil.getTime()) : null;

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the book(s) with the specified criteria?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                adminFunctions.removeBook(title, author, ISBN, publishingDateSql, publishingHouse, genre);
            }
        });
        

        resetButton.addActionListener(e -> {
            // Reset all input fields
            titleInput.setText("");
            authorInput.setText("");
            isbnInput.setText("");
            publishingHouseInput.setText("");
            genreInput.setText("");
            publishingDateInput.setDate(null);
        });
        
        removeBookFrame.setVisible(true);
    }
    
    

    public static void main(String[] args) {
        new AdminRemoveBookPanel();
    }
}

