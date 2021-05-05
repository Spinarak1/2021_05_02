package com.rekrutacyjne.beta;

import com.rekrutacyjne.beta.entity.Post;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BetaApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v1/posts";
    }

    @org.junit.jupiter.api.Test
    public void contextLoads() {
    }

    @org.junit.jupiter.api.Test
    public void testGetAllPosts() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() +"/all",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetPostById() {
        Post post = restTemplate.getForObject(getRootUrl() + "/posts/1", Post.class);
        System.out.println(post.getTitle());
        Assert.assertNotNull(post);
    }

    @Test
    public void testCreatePost() {
        Post post = new Post();
        post.setTitle("iksde");
        post.setId(1);
        post.setBody(" ");
        post.setUserId(1);

        ResponseEntity<Post> postResponse = restTemplate.postForEntity(getRootUrl() + "", post, Post.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePostTitle() {
        int id = 1;
        String test = "The best test";
        Post post = new Post();
        post.setId(id);
        post.setTitle(test);

        restTemplate.put(getRootUrl() + "/editTitle?id=" + id +"&title="+test, post);

        Post updatedPost = restTemplate.getForObject(getRootUrl() +"/editTitle" + id +"&title"+test, Post.class);
        Assert.assertNotNull(updatedPost);
    }
    //Niestety zabrakło czasu na bardziej złożone testy :)
}
