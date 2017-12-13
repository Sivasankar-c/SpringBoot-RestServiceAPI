package com.springboot.rest.fileupload.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.fileupload.service.Document;
@Repository
public interface DocumentServiceDaoRepository extends CrudRepository<Document, Long> {

}
