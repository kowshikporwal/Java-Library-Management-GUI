package com.kowshik061195.library.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.kowshik061195.library.model.Book;
import com.kowshik061195.library.model.Library;
import com.kowshik061195.library.model.VIM;
import com.kowshik061195.library.view.AddBookPanel;
import com.kowshik061195.library.view.BrowseLibraryPanel;
import com.kowshik061195.library.view.LibraryInterface;
import com.kowshik061195.library.view.LoadScreen;
import com.kowshik061195.library.view.MyTableModel;

public class LibrarySystem implements ChangeListener, ActionListener {
	private LibraryInterface screen;
	private AddBookPanel abp;
	private BrowseLibraryPanel blp;
	private LoadScreen ls;
	private Book book;

	private JFileChooser chooser;
	private FileFilter filter, filter2;
	private int resultCode;
	private File vimFile, saveFile, libFile;

	private String fileName;
	private boolean quit;

	private Library lib;
	private List<VIM> vimCache;
	private String[] validFileTypes = { ".wav", ".mp3", ".avi", ".mp4", ".png",
			".jpeg" };
	private byte[] data;
	private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private String[][] dataBook, dataFile;

	private String validFileTypeReminder;

	public LibrarySystem() {
		initEventAttributes();
		screen = new LibraryInterface("Library System");
		abp = screen.getAddBookPanel();
		blp = screen.getBrowseLibraryPanel();

		screen.getTabbedPane().addChangeListener(this);
		abp.addActionListener(this);
		blp.addActionListener(this);
		// 1.prompt the user to choose the file and the file
		// has to end with .ser

		ls = new LoadScreen("Welcome");
		ls.addActionListener(this);
		ls.setVisible(true);

		// 2.a.once entered load the file data ,if the file exists
		// b.otherwise just start the application
		// 3.clear the data if any inside the two table(initially)
		// 4. if file exists the load the book table
		// screen.setVisible(true);
		// perform all the above steps before the screen is visible
	}

	private void initEventAttributes() {
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Video/Image/Music Files", "wav",
				"mp3", "avi", "mp4", "png", "jpeg");
		filter2 = new FileNameExtensionFilter("library files", "ser");
		chooser.addChoosableFileFilter(filter2);
		chooser.addChoosableFileFilter(filter);
		// chooser.setFileFilter(filter);

		lib = new Library();
		vimFile = null;
		saveFile = null;
		vimCache = new ArrayList<VIM>();

		quit = false;

		validFileTypeReminder = "valid type files end with .wav, .mp3, .avi, .mp4, .png, .jpeg";

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == abp.getButtonBrowse()) {
			openChooserAndSetVimFile();
		} else if (event.getSource() == abp.getButtonAddFile()) {
			addVimFileToVimCache();
		} else if (event.getSource() == abp.getButtonAddBook()) {
			addVimFilesInVimCacheToBookAndBookToLibrary();
			reloadDataBook();
			reloadDataFile();
		} else if (event.getSource() == abp.getButtonListAllBooks()) {
			listAllBooks();
		} else if (event.getSource() == abp.getButtonSave()) {
			save();
		} else if (event.getSource() == abp.getButtonSaveAndQuit()) {
			saveAndQuit();
		} else if (event.getSource() == blp.getButtonOpenBook()) {
            openBook();
		} else if (event.getSource() == blp.getButtonDeleteBook()) {

		} else if (event.getSource() == blp.getButtonOpenFile()) {
            openFile();
		} else if (event.getSource() == blp.getButtonDeleteFile()) {

		} else if (event.getSource() == blp.getButtonSave()) {
			save();
		} else if (event.getSource() == blp.getButtonSaveAndQuit()) {
			saveAndQuit();
		} else if (event.getSource() == ls.getButtonNew()) {
			ls.dispose();
			screen.setVisible(true);
		} else if (event.getSource() == ls.getButtonLoad()) {
			// clear the data inside the table if any
			reloadDataBook();
			reloadDataFile();
			loadLibrary();
			chooser.setFileFilter(filter);

		} else if (event.getSource() == ls.getButtonExit()) {
			System.exit(0);
		}
	}

	private void openFile() {
		int row,column;
		VIM v;
		String fileName;
		File file;
		
		row = blp.getFileTable().getSelectedRow();
		column = blp.getFileTable().getSelectedRow();
		fileName = blp.getBookTable().getValueAt(row,column).toString();
		
		v = book.getVimByName(fileName);
		
		try {
			file = new File(v.getName());
			fos = new FileOutputStream(file); // write this file
			fos.write(v.getData());// write this file
			fos.close();
			Desktop.getDesktop().open(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void openBook() {
		int row,column;
		String serialno;
		
		
		row = blp.getBookTable().getSelectedRow();
		column = 3;
		serialno = blp.getBookTable().getValueAt(row,column).toString();
		book = lib.getBookBySerialno(serialno);
		dataFile = book.toStringVectorFiles();
		reloadDataFile();
		
		
		
	}

	private void loadLibrary() {
		chooser.setFileFilter(filter2);
		resultCode = chooser.showOpenDialog(screen);
		if (resultCode == JFileChooser.APPROVE_OPTION) {
			libFile = chooser.getSelectedFile();
			try {
				fis = new FileInputStream(libFile);
				in = new ObjectInputStream(fis);
				lib = (Library) in.readObject();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			ls.dispose();
			// load the book table
			reloadDataBook();

			screen.setVisible(true);
		}

	}

	private void reloadDataBook() {
		while (((MyTableModel) blp.getBookTable().getModel()).getRowCount() > 0) {
			((MyTableModel) blp.getBookTable().getModel()).removeRow(0);
		}

		dataBook = lib.toStringVector();
		for (int i = 0; i < dataBook.length; i++) {
			((MyTableModel) blp.getBookTable().getModel()).addRow(dataBook[i]);
		}
	}

	private void reloadDataFile() {
		while (((MyTableModel) blp.getFileTable().getModel()).getRowCount() > 0) {
			((MyTableModel) blp.getFileTable().getModel()).removeRow(0);
		}
		if (dataFile != null) {
			for (int i = 0; i < dataFile.length; i++) {
				((MyTableModel) blp.getFileTable().getModel()).insertRow(i,
						dataFile[i]);
			}
		}
	}

	private void saveAndQuit() {
		save();
		if (quit)
			System.exit(0);
	}

	private void save() {

		fileName = JOptionPane.showInputDialog(screen,
				"enter filename to save as...", "save",
				JOptionPane.INFORMATION_MESSAGE);
		if (fileName != null) {
			if (!fileName.trim().contentEquals("")) {
				FileOutputStream fos = null;
				ObjectOutputStream out = null;// Serializable

				try {
					saveFile = new File(fileName.trim() + ".ser");
					if (!saveFile.exists()) {
						fos = new FileOutputStream(saveFile);
						out = new ObjectOutputStream(fos);
						out.writeObject(lib);
						fos.close();
						out.close();
						quit = true;
					} else {
						int resultCode = JOptionPane
								.showConfirmDialog(
										screen,
										"\n>file already exists\n do you want to override?",
										"Warning", JOptionPane.YES_NO_OPTION,
										JOptionPane.WARNING_MESSAGE);
						if (resultCode == JOptionPane.YES_OPTION) {
							fos = new FileOutputStream(saveFile);
							out = new ObjectOutputStream(fos);
							out.writeObject(lib);
							fos.close();
							out.close();
							quit = true;
						} else {
							abp.getTextAreaLog().append("\n>save Cancelled");
							quit = false;
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				abp.getTextAreaLog().append("\n>save Cancelled");
				quit = false;
			}
		} else {
			abp.getTextAreaLog().append("\n>save Cancelled");
			quit = false;
		}
	}

	private void listAllBooks() {
		abp.getTextAreaLog().setText(">Listing all books in library...");
		abp.getTextAreaLog().append(" " + lib.toString());

	}

	private void addVimFilesInVimCacheToBookAndBookToLibrary() {
		boolean serialnoAlreadyExist = false;
		boolean allFieldsAreFilled = false;
		int serialno = 0;
		double price = 0.0;
		Book b;

		if (!abp.getTextFieldSerialno().getText().trim().contentEquals("")
				&& !abp.getTextFieldPrice().getText().trim().contentEquals("")
				&& !abp.getTextFieldAuthor().getText().trim().contentEquals("")
				&& !abp.getTextFieldTitle().getText().trim().contentEquals("")) {
			allFieldsAreFilled = true;
		}

		if (allFieldsAreFilled) {
			try {
				serialno = Integer.parseInt(abp.getTextFieldSerialno()
						.getText().trim());
				price = Double.parseDouble(abp.getTextFieldPrice().getText()
						.trim());
				serialnoAlreadyExist = lib.doesSerialnoAlreadyExists(serialno);
				if (serialnoAlreadyExist) {
					JOptionPane
							.showMessageDialog(
									screen,
									abp.getTextFieldSerialno().getText().trim()
											+ "serial no already exists!\n please use another serial no");
				} else {
					b = new Book(serialno, abp.getTextFieldAuthor().getText()
							.trim(), abp.getTextFieldTitle().getText().trim(),
							price);
					for (int i = 0; i < vimCache.size(); i++) {
						b.addVim(vimCache.get(i));
					}
					lib.addBook(b);
					abp.getTextFieldSerialno().setText("");
					abp.getTextFieldPrice().setText("");
					abp.getTextFieldAuthor().setText("");
					abp.getTextFieldTitle().setText("");

					abp.getTextAreaLog().append(
							"\n" + b.getTitle() + " has been added to library");
					vimCache = new ArrayList<VIM>();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(screen, "serial no or price"
						+ abp.getTextFieldSerialno().getText().trim()
						+ "is not a number");
			}
		} else {
			JOptionPane.showMessageDialog(screen,
					"please fill out all the mandatory fields ");
		}
	}

	private void addVimFileToVimCache() {
		if (vimFile != null) {
			for (int i = 0; i < validFileTypes.length; i++) {
				if (abp.getTextFieldFile().getText().trim()
						.endsWith(validFileTypes[i])) {
					data = new byte[(int) vimFile.length()];
					try {
						fis = new FileInputStream(vimFile);
						fis.read(data);
						fis.close();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(screen, "FILE NOT FOUND");
						// "FILE NOT FOUND"
					} catch (IOException e) {
						JOptionPane.showMessageDialog(screen, "FILE NOT FOUND");
						// FILE NOT FOUND
					}
					VIM v = new VIM(abp.getTextFieldFile().getText().trim(),
							data);
					vimCache.add(v);
					vimFile = null;
					abp.getTextAreaLog().append(
							"\n" + abp.getTextFieldFile().getText().trim()
									+ " is ready to be added to book");
					abp.getTextFieldFile().setText("   optional   ");
					return;
				}
			}
			JOptionPane
					.showMessageDialog(screen,
							"Something went wrong!\nplease click browse again and choose your file");

		} else {
			JOptionPane.showMessageDialog(screen, "\n"
					+ abp.getTextFieldFile().getText().trim()
					+ " is not a valid file type!\n" + validFileTypeReminder);
		}
	}

	private void openChooserAndSetVimFile() {
		resultCode = chooser.showOpenDialog(screen);
		if (resultCode == JFileChooser.APPROVE_OPTION) {
			vimFile = chooser.getSelectedFile();
			abp.getTextFieldFile().setText(vimFile.getName());
		}
	}

	@Override
	// called when tab changes
	public void stateChanged(ChangeEvent arg0) {
		// from add book tab browse library tab
		if (screen.getTabbedPane().getSelectedIndex() == 1) {
			screen.getTabbedPane().setTitleAt(
					1,
					screen.getFiller() + screen.getFiller()
							+ " Browse Library " + screen.getFiller()
							+ screen.getFiller());
			screen.setSize(364, 438);
		} else {// from browse tab to library tab
			screen.getTabbedPane().setTitleAt(
					1,
					screen.getFiller() + screen.getFiller() + " Add Book "
							+ screen.getFiller() + screen.getFiller());
			screen.setSize(320, 460);
		}

	}

}
