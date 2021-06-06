package com.sdi.dimage.utils;

import java.util.ArrayList;
import java.util.List;

public class GroupeMetadataModel {
	
	private String titreGroupe;
	
	private List<MetaModel> tags = new ArrayList<>();

	public String getTitreGroupe() {
		return titreGroupe;
	}

	public void setTitreGroupe(String titreGroupe) {
		this.titreGroupe = titreGroupe;
	}

	public List<MetaModel> getTags() {
		return tags;
	}

	public void setTags(List<MetaModel> tags) {
		this.tags = tags;
	}
	
	

}
