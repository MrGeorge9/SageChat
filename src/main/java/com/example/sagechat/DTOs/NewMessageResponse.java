package com.example.sagechat.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewMessageResponse {

    private String content;
    private String created;
    private RegisterResponse author;

    public NewMessageResponse() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        created = dtf.format(now);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public RegisterResponse getAuthor() {
        return author;
    }

    public void setAuthor(RegisterResponse author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "NewMessageResponse{" +
                "content='" + content + '\'' +
                ", created=" + created +
                ", author=" + author +
                '}';
    }
}
