package sg.com.file;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileTransferService {

	@Value("${upload.path}")
	private String path;
	
	private Path filePath = null;
	

	public String storeFile(MultipartFile file) {
		if(filePath == null) {
			setpath();
		}
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
                throw new FileTransferException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			
			Path targetLocation = this.filePath.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		}catch(IOException ex) {
            throw new FileTransferException("Could not store file " + fileName + ". Please try again!", ex);

		}
	}
	
	private void setpath() {
		
		this.filePath = Paths.get(path).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.filePath);
		}catch(Exception ex) {
			throw new FileTransferException("Could not create directory ", ex);
		}		
	}

	public Resource loadFileAsResource(String fileName) {
		if(filePath == null) {
			setpath();
		}
		try {
			Path filePath = this.filePath.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			}else {
				throw new FileNotFoundException("File not found " + fileName );
			}
		}catch(MalformedURLException ex) {
			throw new FileNotFoundException("File not found " + fileName, ex);

		}
	
	}
	
}
