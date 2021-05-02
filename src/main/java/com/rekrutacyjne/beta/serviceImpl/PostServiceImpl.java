package com.rekrutacyjne.beta.serviceImpl;

import com.rekrutacyjne.beta.entity.Post;
import com.rekrutacyjne.beta.repository.PostRepository;
import com.rekrutacyjne.beta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public ResponseEntity<List<Post>> getPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }
    /*@Override
    public ResponseEntity<List<Post>> getPostsByTitle(String title) {
        return new ResponseEntity<>(postRepository.findByTitle(title), HttpStatus.OK);
    }*/
}
