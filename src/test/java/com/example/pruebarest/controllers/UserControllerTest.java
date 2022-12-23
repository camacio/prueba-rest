package com.example.pruebarest.controllers;

import com.example.pruebarest.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    private RestTemplateBuilder builder = new RestTemplateBuilder();
    private TestRestTemplate test = new TestRestTemplate();
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        builder = builder.rootUri("http://localhost:" + port);
    }
    @Test
    void findAll() {
        ResponseEntity<User[]> response = test.getForEntity("/api/users", User[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findOneById() {
        ResponseEntity<User> response = test.getForEntity("/api/users/1", User.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "name": "John",
                    "surname": "Garcia",
                    "age": "31"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<User> response = test.exchange("/api/user/", HttpMethod.POST, request, User.class);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}