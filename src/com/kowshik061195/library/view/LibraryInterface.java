package com.kowshik061195.library.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class LibraryInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private AddBookPanel abp;
	private BrowseLibraryPanel blp;
	private JTabbedPane jtp;
	private String  filler;
	
	public LibraryInterface(String title){
		super(title);
		jtp = new JTabbedPane();
		abp = new AddBookPanel();
		blp = new BrowseLibraryPanel();
		
		filler = "      ";//seven spaces
		jtp.addTab(filler+ filler+ "Add Book"+ filler +filler, abp);
		jtp.addTab(filler+"Browse Libaray"+filler,blp);
		add(jtp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to close the operation in background when close button is hit
		setLocationRelativeTo(null);
		setSize(320,460); //width vs height
		setResizable(false);
	}

	public AddBookPanel getAddBookPanel(){
		return abp;
	}
	public BrowseLibraryPanel getBrowseLibraryPanel(){
		return blp;
	}
	public JTabbedPane getTabbedPane(){
		return jtp;
	}
	public String getFiller(){
		return filler; 
	}
	
	

}
