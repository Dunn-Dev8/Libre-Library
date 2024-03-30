import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //init window and give new Gridlayout() properties
        JFrame window = new JFrame("Libre Library - Dev 2024");
        window.setLayout(new GridLayout(1, 1));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setSize(600, 600);

        //makes a JPannel objext that widgets can be placed on and makes visible
        JPanel panel = new JPanel();
        window.add(panel);

        //init widgets



        JTextField bookIdField = new JTextField("Enter Book ID");
        window.add(bookIdField);

        JTextField studentIdField = new JTextField("Enter Student ID");
        window.add(studentIdField);


        //Top Menu

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Actions");
        JMenu editMenu = new JMenu("Filter");
        JMenu settingsMenu = new JMenu("Settings");

        JMenuItem loanBookMenu = new JMenuItem("Loan Book");
        loanBookMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalMessageDialog(null, "Book_Id is " + bookIdField.getText() + " Student_Id is " + studentIdField.getText());
                //WORK ON HERE
                /*
                INSERT INTO Students(student_id, borrowed_books)
                SELECT 1002, book_id
                FROM Books
                WHERE book_title = 'The Great Gatsby';
                 */
                sqlQServer("", null, null);
            }
        });

        JMenuItem returnBookMenu = new JMenuItem("Return Book");
        returnBookMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalMessageDialog(null, "Book Has Returned");
            }
        });


        JMenuItem searchBookIdByNameMenu = new JMenuItem("Search Book Id by Name");
        searchBookIdByNameMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalMessageDialog(null, "(list of books by their name)");
            }
        });

        JMenuItem redoMenuItem = new JMenuItem("Search Student Id by Name");

        JMenuItem setServerAdder = new JMenuItem("Set mySql Server");
        JMenuItem setBranding = new JMenuItem("Enterprise Branding");


        fileMenu.add(loanBookMenu);
        fileMenu.add(returnBookMenu);

        editMenu.add(searchBookIdByNameMenu);
        editMenu.add(redoMenuItem);

        settingsMenu.add(setServerAdder);
        settingsMenu.add(setBranding);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(settingsMenu);

        window.setJMenuBar(menuBar);


        //code for interacting with a button
        /*addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showInternalMessageDialog(null, "Book Has Been Added");
                sqlQServer("SELECT * FROM Students;;", "getString", "first_name");
            }

            //Menu Bar
            //Loan book | put book_id on loaned book to student in the table
            //return book | take book_id that is in the students borrowed_books out DELETE
            //add book | add book to books table to the database


        });*/

    }

    private static String sqlQServer(String q, String resultsPrompt, String passerResults) {
        try {
            //WHEN CALLING THIS FUNCTION IF YOU WOULD NOT LIKE TO PASS Results OR passerResults PARAMITER TYPE null IN ITS PLACE
            // EXAMPLE OF PROMPT BEING USED sqlQServer("show databases;", "getString", "last_name");
            //last two peramiters are just for searching and collecting data so do not worry to much about them



            //get connection
            Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryData", "root", "1234567890");

            //create a statement
            Statement myStatement = myConnection.createStatement();
            //execute SQL query
            ResultSet myResults = myStatement.executeQuery(q);
            //process output
            while (myResults.next()) {
                System.out.println(myResults);

                if (resultsPrompt == "getString") {
                    System.out.println(myResults.getString(passerResults));
                } else {
                    myStatement.executeQuery("show databases;");
                    System.out.println(myResults);
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        return q;
    }
}