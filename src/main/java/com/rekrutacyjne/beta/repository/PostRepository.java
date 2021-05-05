package com.rekrutacyjne.beta.repository;

import com.rekrutacyjne.beta.entity.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
    Optional<List<Post>> findByTitleContaining(String name);
    Optional<Post> findTopByOrderByIdDesc();
}
