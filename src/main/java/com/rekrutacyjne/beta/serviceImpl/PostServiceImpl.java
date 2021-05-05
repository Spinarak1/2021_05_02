package com.rekrutacyjne.beta.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rekrutacyjne.beta.entity.Post;
import com.rekrutacyjne.beta.entity.PostDto;
import com.rekrutacyjne.beta.mapper.PostMapper;
import com.rekrutacyjne.beta.repository.PostRepository;
import com.rekrutacyjne.beta.service.PostService;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;


    public CloseableHttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContexts.custom().build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new String[]{"TLSv1.2", "TLSv1.1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        return HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build();
    }

    public Optional<List<Post>> readFromExternalApi() throws KeyManagementException, NoSuchAlgorithmException {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(getHttpClient()));
        ResponseEntity<List<Post>> postResponse =
                restTemplate.exchange("https://jsonplaceholder.typicode.com/posts",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
                        });
        List<Post> post = postResponse.getBody();
        return Optional.of(post);
    }
    @Override
    public Optional<List<Post>> saveDataFromExternalApi() throws NoSuchAlgorithmException, KeyManagementException, JsonProcessingException {
        List<Post> posts = readFromExternalApi().get();
        List<Post> newAddedPosts = new ArrayList<>();
        if(posts != null) {
            for (Post i : posts) {
                if(i != null) {
                    if(!existsById(i.getId())) {
                        this.save(i);
                        newAddedPosts.add(i);
                    }
                }
            }
        }
        return Optional.of(newAddedPosts);
    }
    @Override
    public Optional<List<Post>> fiterDataFromExternalApi(String title) throws NoSuchAlgorithmException, KeyManagementException {
        List<Post> posts = readFromExternalApi().get();
        return findByTitle(title);
    }

    @Override
    public Optional<List<Post>> getPosts() {
        return Optional.of(postRepository.findAll());
    }
    @Override
    public Optional<List<Post>> findByTitle(String title){
        return postRepository.findByTitleContaining(title);
    }
    @Override
    public Optional<Post> findById(Integer id){
        return postRepository.findById(id);
    }

    @Override
    public Boolean existsById(Integer id) {
        return postRepository.existsById(id);
    }

    @Override
    public void delete(Integer id){
        postRepository.deleteById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    /*@Override
    public ResponseEntity<List<Post>> getPostsByTitle(String title) {
        return new ResponseEntity<>(postRepository.findByTitle(title), HttpStatus.OK);
    }*/
}
