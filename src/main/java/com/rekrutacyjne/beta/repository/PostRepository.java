package com.rekrutacyjne.beta.repository;

import com.rekrutacyjne.beta.entity.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    ResponseEntity<List<Post>> findByTitle(String title);
}
