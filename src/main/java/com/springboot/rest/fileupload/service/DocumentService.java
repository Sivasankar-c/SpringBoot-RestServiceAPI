package com.springboot.rest.fileupload.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import com.springboot.rest.beans.ResponseMetaData;

public interface DocumentService {
	
	ResponseMetaData save(MultipartFile multipartFile);
	
	byte[] getDocument(Long id);
	
	List<Document> findAll();
	
	

}
