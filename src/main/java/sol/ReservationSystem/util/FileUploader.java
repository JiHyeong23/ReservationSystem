package sol.ReservationSystem.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class FileUploader {
    private final String UPLOAD_PATH = "/Users/gimjihyeong/ReservationSystem/db/image/";
    public String saveImage(MultipartFile image, String beforeImagePath) {
        if (beforeImagePath != null) {
            File file = new File(beforeImagePath);
            file.delete();
        }
        String fileName = UUID.randomUUID().toString();
        String path = UPLOAD_PATH + fileName;
        System.out.println(path);
        try {
            image.transferTo(new File(path));
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return path;
    }

    public void deleteImage(String imagePath) {
        File deleteFile = new File(imagePath);
        deleteFile.delete();
    }
}
