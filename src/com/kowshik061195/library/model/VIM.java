package com.kowshik061195.library.model;

import java.io.Serializable;

public class VIM implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private byte[] data;
	
	public VIM(){
		this.name = null;
		this.data = null;
	}
	public VIM(String name, byte[] data){
		this.name = name;
		this.data = data;
	}
	
	public String getName(){
		return this.name;
	}
	public byte[] getData(){
		return this.data;
	}
	

}
