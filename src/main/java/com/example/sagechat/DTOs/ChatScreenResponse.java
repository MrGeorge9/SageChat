package com.example.sagechat.DTOs;

import java.util.ArrayList;
import java.util.List;

public class ChatScreenResponse {

    private List<NewMessageResponse> messages = new ArrayList<>();
    private NewChannelResponse channel;

    public ChatScreenResponse() {
    }

    public List<NewMessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<NewMessageResponse> messages) {
        this.messages = messages;
    }

    public NewChannelResponse getChannel() {
        return channel;
    }

    public void setChannel(NewChannelResponse channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "ChatScreenResponse{" +
                "messages=" + messages +
                ", channel=" + channel +
                '}';
    }
}
