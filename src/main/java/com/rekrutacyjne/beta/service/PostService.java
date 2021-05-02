package com.rekrutacyjne.beta.service;

import com.rekrutacyjne.beta.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    ResponseEntity<List<Post>> getPosts();
    //ResponseEntity<List<Post>> getPostsByTitle(String title);
}
