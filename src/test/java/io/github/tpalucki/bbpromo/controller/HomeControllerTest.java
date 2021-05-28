package io.github.tpalucki.bbpromo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    private static final String API_URL = "/";
    private static final String API_URL_WITH_QUERY = "/?query=42095";
    private static final String API_URL_WITH_PAGE = "/?page=2";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldShowIndexPage() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains(
                "favicon.ico",
                "LEGO Disney 10775 - Farma Mikiego i Donalda", "2021", "118", "127.99", "PLN",
                "LEGO Marvel Superheroes 76193 - Statek strażników", "2021", "1901", "569.99", "PLN"
        );
    }

    @Test
    void shouldShowIndexPageWithQuery() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL_WITH_QUERY, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains(
                "favicon.ico",
                "LEGO Disney 10775 - Farma Mikiego i Donalda", "2021", "118", "127.99", "PLN",
                "LEGO Marvel Superheroes 76193 - Statek strażników", "2021", "1901", "569.99", "PLN"
        );
    }

    @Test
    void shouldShowIndexPageWithPageFilter() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL_WITH_PAGE, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains(
                "favicon.ico",
                "LEGO Disney 10775 - Farma Mikiego i Donalda", "2021", "118", "127.99", "PLN",
                "LEGO Marvel Superheroes 76193 - Statek strażników", "2021", "1901", "569.99", "PLN"
        );
    }
}