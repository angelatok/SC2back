package sg.com.file;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/res")
public class FileTransferController {

	
	@Autowired
	private FileTransferService fileTransferService;
	
	
	@PostMapping("/uploadFile")
	public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file ) {
		
		String fileName = fileTransferService.storeFile(file);

		String fileDownloadUri = 
				ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/res/downloadFile/")
				.path(fileName)
				.toUriString();
		
		return new FileUploadResponse(fileName, fileDownloadUri,
				file.getContentType(), file.getSize());
	}
	
	@PostMapping("/uploadMultipleFiles")
	public List<FileUploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
		return Arrays.asList(files)
				.stream()
				.map(file->uploadFile(file))
				.collect(Collectors.toList());
		
	}
	
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		Resource resource = fileTransferService.loadFileAsResource(fileName);
		
		String contentType = null;
		try {
            contentType = request.getServletContext()
            		.getMimeType(resource.getFile().getAbsolutePath());
		}catch(IOException ex) {
            log.info("Could not determine file type.");
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
			
			

}
