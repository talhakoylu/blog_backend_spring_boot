package backend.api.controller;

import backend.service.serviceInterface.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/uploads")
@RestController
@AllArgsConstructor
public class FileServeController {

    private ImageService imageService;

    @GetMapping("images/{imageName:.+}")
    public ResponseEntity<Object> getImageByImagePath(@PathVariable String imageName){
        System.out.println("imagepath: " + imageName);
        System.out.println("istek geldi");
        return this.imageService.getImageByPath(imageName);
    }

    @GetMapping("deneme")
    public String deneme(){
        return "deneme";
    }
}
