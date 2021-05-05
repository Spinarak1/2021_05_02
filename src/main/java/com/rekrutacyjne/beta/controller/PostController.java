package com.rekrutacyjne.beta.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rekrutacyjne.beta.entity.Post;
import com.rekrutacyjne.beta.entity.PostDto;
import com.rekrutacyjne.beta.mapper.PostMapper;
import com.rekrutacyjne.beta.service.PostService;
import com.rekrutacyjne.beta.serviceImpl.PostServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Post>> getAll(){
        return postService.getPosts().map(posts -> new ResponseEntity<>(posts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Integer id) {
        return postService.findById(id).map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Transactional
    @PostMapping("")
    public ResponseEntity<Post> addPost(@RequestParam("post") String post) throws JsonProcessingException {
        PostDto postDto;
        postDto = new ObjectMapper().readValue(post, PostDto.class);

        return new ResponseEntity<>(postService.save(PostMapper.INSTANCE.postDtoToPost(postDto)), HttpStatus.CREATED);
    }
    @Transactional
    @RequestMapping(value = "/editTitle", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Post> editTitle(@RequestParam("id") Integer id, @RequestParam String title) throws JsonProcessingException {
        if(postService.existsById(id)) {
            Post post;
            post = postService.findById(id).get();
            post.setTitle(title);
            PostDto postDto;
            return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Transactional
    @RequestMapping(value = "/editBody", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Post> editBody(@RequestParam("id") Integer id, @RequestParam String body) throws JsonProcessingException {
        if(postService.existsById(id)) {
            Post post;
            post = postService.findById(id).get();
            post.setBody(body);
            PostDto postDto;
            return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam Integer id) {
        if(postService.existsById(id)) {
            postService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/findByTitle")
    public ResponseEntity<List<Post>> getPostsByName(@RequestParam String title) {
        return postService.findByTitle(title).map(posts -> new ResponseEntity<>(posts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
    @GetMapping("/addPostFromExternalApi")
    public ResponseEntity<List<Post>> addPostFromExternalApi() throws KeyManagementException, NoSuchAlgorithmException, JsonProcessingException {
        return postService.saveDataFromExternalApi().map(posts -> new ResponseEntity<>(posts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
    @GetMapping("/findByTitleFromExternalApi")
    public ResponseEntity<List<Post>> getPostsByNameFromExternalApi(@RequestParam String title) throws KeyManagementException, NoSuchAlgorithmException {
        return postService.fiterDataFromExternalApi(title).map(posts -> new ResponseEntity<>(posts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
