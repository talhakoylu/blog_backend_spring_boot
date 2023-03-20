package backend.core.utils.fileUpload;

import backend.core.utils.exceptions.FileUploadServiceException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.util.BufferedImages;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ImageOptimizer {

    private final Logger log = LoggerFactory.getLogger(ImageOptimizer.class);

   /* public byte[] compressor(MultipartFile image, float compressionQuality) throws IOException {
        System.out.println("çalıştı");
        if (!Objects.equals(image.getContentType(), "image/jpeg") &&
                !Objects.equals(image.getContentType(), "image/png")) {
            log.info("ImageOptimizerCompressor will not work because of content type not equals image/jpeg or image/png");
            return null;
        }

        byte[] result = null;

        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
        Iterator<ImageWriter> imageWriterIterator = ImageIO.getImageWritersByMIMEType(image.getContentType());

        if (!imageWriterIterator.hasNext())
            throw new FileUploadServiceException("Something went wrong while image processing.");
        ImageWriter imageWriter = imageWriterIterator.next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

        System.out.println("çalıştı 2");


        try {
            imageWriter.setOutput(imageOutputStream);
            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(compressionQuality);
            System.out.println("çalıştı 3");

            imageWriter.write(
                    null,
                    new IIOImage(bufferedImage, null, null),
                    imageWriteParam
            );
            result = byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            log.error("ImageOptimizerCompressorIOException", e);
            throw new FileUploadServiceException("Something went wrong while image processing.");
        } finally {
            System.out.println("çalıştı 4");

            byteArrayOutputStream.close();
            imageOutputStream.close();
            imageWriter.dispose();
        }
        System.out.println("burada patladı");
        System.out.println("result: " + result);


        return result;
    }*/

    public byte[] compressor(MultipartFile image, float quality) {
        String imageType = null;

        if (Objects.equals(image.getContentType(), "image/jpeg")) {
            imageType = "jpeg";
        } else if (Objects.equals(image.getContentType(), "image/png")) {
            imageType = "png";
        } else {
            return null;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (InputStream inputStream = image.getInputStream(); outputStream;) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            Thumbnails.of(bufferedImage)
                    .size(bufferedImage.getWidth(), bufferedImage.getHeight())
                    .keepAspectRatio(true)
                    .outputQuality(quality)
                    .outputFormat(imageType)
                    .toOutputStream(outputStream);

        } catch (IOException e) {
            log.error("ImageOptimizerCompressorIOException", e);
            throw new FileUploadServiceException("Something went wrong while image processing.");
        }

        return outputStream.toByteArray();
    }

}
