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
class ProductDetailsControllerTest {

    private static final String API_URL = "/products/1";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldShowIndexPage() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains(List.of(
                "favicon.ico",
                "LEGO® 10775 Disney - Farma Mikiego i Donalda",
                "Tytuł oryginalny:", "Mickey Mouse & Donald Duck's Farm",
                "Numer katalogowy:", "10775",
                "Seria:", "LEGO Disney",
                "Liczba elementów:", "118",
                "Rok wydania:", "2021",
                "Cena oryginalna:", "139.99",
                "127.99",
                "(Allegro, 26.05.2021)",
                "PLN",
                "4"

        ));
    }
}