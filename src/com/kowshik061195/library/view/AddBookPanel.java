package com.kowshik061195.library.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddBookPanel extends JPanel { //JPanel implements Serializable

	private static final long serialVersionUID = 1L;
	
	private Box mainBox, hBox1, hBox2 ,hBox3 ,hBox4 ,hBox5 ,hBox6 ,hBox7, hBox8;
	

	private JLabel jlSerialno,jlAuthor,jlTitle,jlPrice,jlFile,jlLogDog;
	private JTextField  jtSerialno, jtAuthor,jtTitle,jtPrice,jtFile;
	private JButton bBrowse,bAddFile,bAddBook,bSave,bSaveAndQuit,bListAllBooks;
	private JTextArea jtaLog;
	private JScrollPane scrollPane;
	
	public AddBookPanel(){
		super(new FlowLayout() ); //implicit but just to make it explicit its been passed
		initWidgets();
		addWidgets();
		setBackground(new Color(94,230,248));
	}

	
	private void initWidgets() {
		mainBox = Box.createVerticalBox();
		hBox1 = Box.createHorizontalBox();
		hBox2 = Box.createHorizontalBox();
		hBox3 = Box.createHorizontalBox();
		hBox4 = Box.createHorizontalBox();
		hBox5 = Box.createHorizontalBox();
		hBox6 = Box.createHorizontalBox();
		hBox7 = Box.createHorizontalBox();
		hBox8 = Box.createHorizontalBox();
		
		jlSerialno = new JLabel("Enter Serial No:    ");
		jlAuthor = new JLabel("Enter Author:        ");
		jlTitle = new JLabel("Enter Title:             ");
		jlPrice = new JLabel("Enter Price:           ");
		jlFile  = new JLabel("Add New File:        ");
		jlLogDog =  new JLabel("LogDog:                                   ");
		
		jtSerialno = new JTextField(19);
		jtAuthor = new JTextField(19);
		jtTitle = new JTextField(19);
		jtPrice = new JTextField(19);
		jtFile = new JTextField(19);
		
		jtFile.setText("optional");
		
		//jtSerialno.setHorizontalAlignment(JTextField.RIGHT);
		// to write from right to left;
		bBrowse = new JButton("Browse");
		bAddFile = new JButton("Add file to Book");
		bAddBook = new JButton("Add book to Library");
		bSave = new JButton("Save");
		bSaveAndQuit = new JButton("Save and Quit");
		bListAllBooks = new JButton("List all books");
		
		
		jtaLog = new JTextArea(10,24);
		jtaLog.setEditable(false);//so that it cannot be edited
		
		scrollPane = new JScrollPane(jtaLog);//applying sroll to logdog textarea
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
	}
	
	private void addWidgets() {
		hBox1.add(jlSerialno);
		hBox1.add(jtSerialno);
		hBox2.add(jlAuthor);
		hBox2.add(jtAuthor);
		hBox3.add(jlTitle);
		hBox3.add(jtTitle);
		hBox4.add(jlPrice);
		hBox4.add(jtPrice);  // hBox adds Horizontally defined in initWidgets.
		hBox5.add(jlFile);
		hBox5.add(jtFile);
		hBox6.add(Box.createHorizontalStrut(2));
		hBox6.add(bBrowse);
		hBox6.add(Box.createHorizontalStrut(5));
		hBox6.add(bAddFile);
		hBox7.add(jlLogDog);
	    //hBox6.add(Box.createHorizontalStrut(80));
		hBox7.add(bAddBook);
		hBox8.add(bListAllBooks);
		hBox8.add(Box.createHorizontalStrut(20));
		hBox8.add(bSave);
		hBox8.add(Box.createHorizontalStrut(5));
		hBox8.add(bSaveAndQuit);
		
		mainBox.add(hBox1);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox2);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox3);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox4);  // mainBox add vertically defined in initWidgets
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox5);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox6);
		mainBox.add(Box.createVerticalStrut(5));
		//mainBox.add(jtaLog);
		mainBox.add(hBox7);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(scrollPane); // text area because scroll pane contain text area
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(hBox8);
		
		add(mainBox); //adding mainBox in panel.
		
		
		
		
	}

    
	public void addActionListener(ActionListener a) {
		bBrowse.addActionListener(a);
		bAddFile.addActionListener(a);
		bAddBook.addActionListener(a);
		bSave.addActionListener(a);
		bSaveAndQuit.addActionListener(a);
		bListAllBooks.addActionListener(a);
		
	}


	public JButton getButtonBrowse() {
		return bBrowse;
	}
	public JButton getButtonListAllBooks() {
		return bListAllBooks;
	}
	public JButton getButtonAddFile() {
		return bAddFile;
	}
	public JButton getButtonAddBook() {
		return bAddBook;
	}
	public JButton getButtonSave() {
		return bSave;
	}
	public JButton getButtonSaveAndQuit() {
		return bSaveAndQuit;
	}
	//jlSerialno, jlAuthor,jlTitle,jlPrice,jlFile,;
	public JTextField getTextFieldSerialno(){
		return jtSerialno;
	}
	public JTextField getTextFieldAuthor(){
		return jtAuthor;
	}
	public JTextField getTextFieldTitle(){
		return jtTitle;
	}
	public JTextField getTextFieldPrice(){
		return jtPrice;
	}
	public JTextField getTextFieldFile(){
		return jtFile;
	}
	
	public JTextArea getTextAreaLog(){
		return jtaLog;
	}
	
	
	

	

	

}
