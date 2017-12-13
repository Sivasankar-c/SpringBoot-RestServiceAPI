package com.springboot.rest.fileupload.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.springboot.rest.beans.ResponseMetaData;
import com.springboot.rest.fileupload.service.DocumentServiceImpl;

@Controller
public class FileUploadController {
	
	@Autowired
	private DocumentServiceImpl documentService;
	
	@GetMapping("/")
    public String defaultViewPage() {		
		
        return "upload";
    }
	
	@PostMapping(value="/api/upload")
	public String uploadFile(@RequestParam(value="file") MultipartFile file,RedirectAttributes redirectAttributes){
				
		if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
		documentService.save(file);	
		redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");
		
		return "redirect:/uploadStatus";
		
	}
	
	@RequestMapping(value = "/document/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> retrieveDocument(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(documentService.getDocument(id), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<com.springboot.rest.fileupload.service.Document> getDocument() {
        return documentService.findAll();
    }
    
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
	
    
    

}
