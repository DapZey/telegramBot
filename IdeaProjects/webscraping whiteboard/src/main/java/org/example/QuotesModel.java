package org.example;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties (ignoreUnknown = true)
public class QuotesModel {
    String author;
    public String getAuthor() {
        return author;
    }
    String content;
    public String getContent() {
        return content;
    }
}
