package com.example.sagechat.DTOs;

public class RegisterResponse {

    private String username;
    private Integer userId;
    private String avatarUrl;

    public RegisterResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
