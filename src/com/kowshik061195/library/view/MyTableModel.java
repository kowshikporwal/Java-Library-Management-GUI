package com.kowshik061195.library.view;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{

	
	private static final long serialVersionUID = 1L;
    public MyTableModel(String[][] data, String[] columns){
    	super(data,columns);
    }
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		
		return false;
	}
	

}
