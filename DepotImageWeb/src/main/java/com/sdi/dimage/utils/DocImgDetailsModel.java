package com.sdi.dimage.utils;

import java.util.ArrayList;
import java.util.List;

public class DocImgDetailsModel extends DocImgModel {
	
	private List<Integer> idsImage = new ArrayList<>();

	public List<Integer> getIdsImage() {
		return idsImage;
	}

	public void setIdsImage(List<Integer> idsImage) {
		this.idsImage = idsImage;
	}

	
}
