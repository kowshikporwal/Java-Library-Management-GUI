package com.kowshik061195.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<Book> collection;

	public Library() {
		collection = new ArrayList<Book>();
	}

	public void addBook(Book book) {
		collection.add(book);
	}

	@Override
	public String toString() {
		String total = "\n";
		/*
		 * for(int i=0;i<collection.size();i++){ Book b = collection.get(i);
		 * total = total+b.toString(); }
		 */
		Iterator<Book> i = collection.iterator();
		while (i.hasNext()) {
			Book b = (Book) i.next();
			total = total + b.toString();
		}
		return total;
	}

	public boolean doesSerialnoAlreadyExists(int serialno) {
		Iterator<Book> i = collection.iterator();
		while (i.hasNext()) {
			if(i.next().getSerialno() == serialno){
				return true;
			}
		}
		return false;
	}
	
	public String[][] toStringVector(){
		String[][]  total = new String[collection.size()][5];
		
		for(int i=0; i<collection.size(); i++){
			total[i][0] = collection.get(i).getTitle();
			total[i][1] = collection.get(i).getAuthor();
			total[i][2] = collection.get(i).getPrice();
			total[i][3] = collection.get(i).getSerialno1();
			
		}
		return total;
		
		
	}

	public Book getBookBySerialno(String serialno) {
		for(int i=0;i<collection.size();i++){
			if(collection.get(i).getSerialno1().contentEquals(serialno)){
				return collection.get(i);
			}
		}
		return null;
	}
}
