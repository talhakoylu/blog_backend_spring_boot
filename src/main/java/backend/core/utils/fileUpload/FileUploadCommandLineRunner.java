package backend.core.utils.fileUpload;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FileUploadCommandLineRunner implements CommandLineRunner {

    private FileUploadService fileUploadService;

    @Override
    public void run(String... args) throws Exception {
        this.fileUploadService.init();
    }
}
