package com.bolsadeideas.springboot.app.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

	public Resource load(String filename) throws Exception;
	public String copy(MultipartFile file)  throws Exception;
	public boolean delete(String filename);
	public void deleteAll();
	public void init() throws IOException;
}
