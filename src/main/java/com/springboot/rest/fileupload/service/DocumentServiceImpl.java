package com.springboot.rest.fileupload.service;

import java.io.IOException;
import java.util.List;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.rest.beans.ResponseMetaData;
//import com.springboot.rest.fileupload.Dao.DocumentServiceDaoRepository;
import com.springboot.rest.fileupload.Dao.DocumentServiceDaoRepository;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	
	private static  Logger logger = org.slf4j.LoggerFactory.getLogger(DocumentServiceImpl.class);
	
	@Autowired
	private DocumentServiceDaoRepository repository;

	@Override
	public ResponseMetaData save(MultipartFile file)  {	
		ResponseMetaData response = null;		
		try {
			Document document = new Document();
			document.setDocName(file.getOriginalFilename());
			document.setFile(file.getBytes());
			repository.save(document);
			
			response = new ResponseMetaData();
			response.setStatus(200);
			response.setMessage("Success");
			return response;
			
			
		} catch (IOException e) {			
		   logger.error("IO exception occured:"+e.getMessage());
		}
		return response;
		
	}

	@Override
	public byte[] getDocument(Long id) {		
		
		return repository.findOne(id).getFile();
		
	}

	@Override
	public List<Document> findAll() {
		 return (List<Document>) repository.findAll();
		
	}
	
	

}
