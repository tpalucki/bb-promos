package io.github.tpalucki.bbpromo.model;

import lombok.Value;

@Value
public class Article {

    String title;
    String publishDate;
    String author;
    String body;
}
