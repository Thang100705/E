package com.example.test.services;

import com.example.test.models.Images;
import com.example.test.respositories.ImageRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final String uploadDir = "uploads"; // Thư mục lưu ảnh

    @Autowired
    private ImageRespo imagesRepository;

    // Lưu ảnh vào hệ thống file và cơ sở dữ liệu
    public Images saveImage(MultipartFile file, Long postId) throws IOException {
        // Tạo tên tệp và loại tệp
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String fileType = file.getContentType();

        // Lưu tệp vào hệ thống file
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // Lưu thông tin ảnh vào cơ sở dữ liệu
        Images image = new Images();
        image.setFile_name(fileName);
        image.setFileType(fileType);
        image.setFilePath(filePath.toString()); // Lưu đường dẫn tệp vào cơ sở dữ liệu
        image.setPostId(postId); // Liên kết với bài viết thông qua postId

        // Lưu ảnh vào cơ sở dữ liệu
        return imagesRepository.save(image);
    }

    // Lấy ảnh từ cơ sở dữ liệu theo ID
//    public Optional<Images> getImageById(Long id) {
//        return imagesRepository.findById(id);
//    }
    public Optional<Images>getImageById(Long id){
        return imagesRepository.findById(id);
    }

    public List<Images> getImageByPostId(Long id){
        return imagesRepository.findByPostId(id);
    }
}
