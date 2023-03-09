package backend.api.controller;

import backend.core.utils.fileUpload.FileUploadService;
import backend.service.reqResModel.image.AddImageRequest;
import backend.service.reqResModel.image.AddImageResponse;
import backend.service.reqResModel.image.CreateImageRequest;
import backend.service.reqResModel.image.CreateImageResponse;
import backend.service.serviceInterface.ImageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("/api/image")
@AllArgsConstructor
public class ImageController {

    private ImageService imageService;
    private FileUploadService fileUploadService;

    @PostMapping("add")
    public AddImageResponse addImage(AddImageRequest addImageRequest){
        return this.imageService.add(addImageRequest);
    }


    @PostMapping("upload-image")
    public CreateImageResponse uploadImage(@ModelAttribute CreateImageRequest createImageRequest){
        return this.imageService.uploadImage(createImageRequest);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileUploadService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/files2/{filename:.+}")
    @ResponseBody
    public FileInfo loadFile(@PathVariable String filename) {

        String url = MvcUriComponentsBuilder
                .fromMethodName(ImageController.class, "getFile", filename).build().toString();

        return new FileInfo(filename, url);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class FileInfo{
        private String fileName;
        private String url;
    }

}
