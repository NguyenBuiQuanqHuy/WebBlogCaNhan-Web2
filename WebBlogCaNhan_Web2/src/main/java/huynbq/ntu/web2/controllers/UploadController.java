package huynbq.ntu.web2.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
	@PostMapping("/api/upload-image")
	public ResponseEntity<?> uploadImageFromTinyMCE(@RequestParam("file") MultipartFile file) throws IOException {
	    if (file.isEmpty()) {
	        return ResponseEntity.badRequest().body("File rỗng");
	    }

	    String uploadDir = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	    Files.createDirectories(Paths.get(uploadDir));

	    String filename = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());

	    Path filePath = Paths.get(uploadDir, filename);
	    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	    String fileUrl = "/image/" + filename;

	    return ResponseEntity.ok(new UploadResponse(fileUrl));
	}

	public static class UploadResponse {
	    private String url;
	    public UploadResponse(String url) { this.url = url; }
	    public String getUrl() { return url; }
	    public void setUrl(String url) { this.url = url; }
	}

}
