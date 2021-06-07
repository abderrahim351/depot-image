package com.sdi.dimage.utils;

public class MetaModel {
	
	private String directory;
	
    private String tag ;
    private String valeur ;
    
    
    
	public MetaModel() {
		super();
	}
	
	public MetaModel(String directory) {
		this.directory = directory;
	}
	
	

	public MetaModel(String tag, String valeur) {
		super();
		this.tag = tag;
		this.valeur = valeur;
	}

	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
    
}
