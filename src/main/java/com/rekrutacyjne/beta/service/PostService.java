package com.rekrutacyjne.beta.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rekrutacyjne.beta.entity.Post;
import org.springframework.http.ResponseEntity;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<List<Post>> getPosts();
    Optional<List<Post>> readFromExternalApi() throws KeyManagementException, NoSuchAlgorithmException;
    Boolean existsById(Integer id);
    Optional<Post> findById(Integer id);
    Optional<List<Post>> findByTitle(String title);
    void delete(Integer id);
    Post save(Post post);
    Optional<List<Post>> saveDataFromExternalApi() throws NoSuchAlgorithmException, KeyManagementException, JsonProcessingException;
    Optional<List<Post>> fiterDataFromExternalApi(String title) throws NoSuchAlgorithmException, KeyManagementException;
    public Optional<List<Post>> externalApiDataExceptDeletedAndModified() throws NoSuchAlgorithmException, KeyManagementException;

}
