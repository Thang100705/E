package com.example.test.respositories;
import com.example.test.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRespo extends JpaRepository<Images,Long> {

    List<Images> findByPostId(Long postId);


}
