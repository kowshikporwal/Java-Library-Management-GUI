package com.kowshik061195.library.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int serialno;
	private String author, title;
	private double price;

	private List<VIM> vims;
	
	public Book(int serialno, String author, String title, double price) {
		this.serialno = serialno;
		this.author = author;
		this.title = title;
		this.price = price;
		vims = new ArrayList<VIM>();
	}

	public void addVim(VIM v) {
		vims.add(v);
	}
   


	public VIM getVimByName(String name) {
		VIM v = null;
		Iterator<VIM> i = vims.iterator();
		while (i.hasNext()) {
			v = i.next();
			if (v.getName().toLowerCase().contentEquals(name.toLowerCase())) {
				return v;
			}
		}
		return null;
	}
	@Override
	public String toString() {
		String vimNames = "";
		String vimAmount = "(" + String.valueOf(vims.size()) + ")";

		Iterator<VIM> i = vims.iterator();
		while (i.hasNext()) {
			vimNames += i.next().getName() + ",";
		}
		return "\nTitle :" + title + "\nAuthor :" + author + "\nSerialNo :"
				+ serialno + "\nprice :" + price + "\nVIMFile" + vimAmount
				+ ":" + vimNames + "\n";
	}
	
	public int getSerialno(){
		return serialno;
	}

	public String getAuthor() {
		return author;
	}
	
	public String getPrice() {
		return String.valueOf(price);
	}
	public String getTitle() {
		return title;
	}
	public String getSerialno1() {
		return String.valueOf(serialno);
	}

	public String[][] toStringVectorFiles() {
		String total[][] = new String[vims.size()][3];
		VIM v;
		for(int i=0;i<vims.size();i++){
			v = vims.get(i);
			if(v.getName().endsWith("wav")|| v.getName().endsWith("mp3")) {
				total[i][0] = v.getName();
			}else if(v.getName().endsWith("png")|| v.getName().endsWith("jpeg")){
				total[i][1] = v.getName();
			}else{
				total[i][2] = v.getName();
			}
		}
		return total;
	}
    
}
