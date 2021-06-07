package com.sdi.dimage.utils;

import java.util.ArrayList;
import java.util.List;

public class MetaTreeNodeModel {
	

	private MetaModel data;
	
	private List<MetaTreeNodeModel> children;

	public MetaTreeNodeModel() {
	}

	public MetaTreeNodeModel(String directory) {
		super();
		data = new MetaModel(directory);
		children = new ArrayList<>();
	}
	
	public MetaTreeNodeModel(String tag, String valeur) {
		super();
		data = new MetaModel(tag,valeur);
	}

	public MetaModel getData() {
		return data;
	}

	public void setData(MetaModel data) {
		this.data = data;
	}

	public List<MetaTreeNodeModel> getChildren() {
		return children;
	}

	public void setChildren(List<MetaTreeNodeModel> children) {
		this.children = children;
	}
	
	
	
}
