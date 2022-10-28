package com.example.sagechat.DTOs;

public class MessagesOnChannel {

    private String messageContent;
    private String date;
    private String author;

    public MessagesOnChannel() {
    }

    public MessagesOnChannel(String messageContent, String date, String author) {
        this.messageContent = messageContent;
        this.date = date;
        this.author = author;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
