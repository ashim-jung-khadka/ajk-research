package io.ashimjk.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class JsonPlaceholderClientTest {

    @Autowired
    private JsonPlaceholderClient client;


    @Test
    void givenJPlaceholderClient_shouldRunSuccessfully() {
        List<Post> posts = client.getPosts();

        posts.forEach(System.out::println);

        assertTrue("post size should be greater than 2", posts.size() > 2);
    }

    @Test
    void givenPostId_shouldReturnPost() {
        Post post = client.getPostById(1);
        System.out.println(post);

        assertTrue("post should not be null", post != null);
    }


}