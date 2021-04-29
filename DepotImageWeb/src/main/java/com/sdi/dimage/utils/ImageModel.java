package com.sdi.dimage.utils;

import org.springframework.web.multipart.MultipartFile;

public class ImageModel {
	private MultipartFile img;
	private int id ;
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
