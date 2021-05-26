package io.github.tpalucki.bbpromo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    private static final String API_URL = "/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldShowIndexPage() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains(List.of(
                "Set A", "01.04.2021", "1234", "This is some piece of description",
                "Set B", "01.04.2021", "72863", "Lorem ipsum et dolores summit"
        ));
    }
}