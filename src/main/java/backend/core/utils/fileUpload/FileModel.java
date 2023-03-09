package backend.core.utils.fileUpload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {

    private String originalPath;

    private String convertedPath;

    private String uniqueFileName;

    private String originalFileName;

    private long fileSize;
}
