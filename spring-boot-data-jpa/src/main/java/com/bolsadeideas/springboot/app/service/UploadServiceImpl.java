package com.bolsadeideas.springboot.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements IUploadService{

	private final static String UPLOADS_FOLDER="uploads";
	
	@Override
	public Resource load(String filename) throws Exception {
		
		Path pathFoto = obtienePath(filename);
		
		Resource recurso = new UrlResource(pathFoto.toUri());
			
			if(!recurso.exists() || !recurso.isReadable())
				throw new RuntimeException("Error: no se puede cargar la imagen: "+pathFoto.toString());
			
		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws Exception {
		
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		Path rootPath = obtienePath(uniqueFilename);
		
		Files.copy(file.getInputStream(), rootPath);
			
		return uniqueFilename;
		
	}

	@Override
	public boolean delete(String filename) {
		
		Path pathFoto = obtienePath(filename);
		File archivo = pathFoto.toFile();
		
		if(archivo.exists() && archivo.canRead()) {
			return archivo.delete();
		}
		
		return false;
	}

	private Path obtienePath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
	}

	@Override
	public void init() throws IOException {
		Files.createDirectory(Paths.get(UPLOADS_FOLDER));
	}
		
}
