package com.bigdata.chunks.util;

public class OptionItem {
	
	private String id;    
	private String label;
	
	public OptionItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OptionItem( String id, String label ){
		this.id = id;
		this.label = label;    
	}
	
	public String getId(){ 
		return this.id; 
	}    
	
	public String getLabel(){
		return this.label;
	}
}
