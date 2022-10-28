package com.example.sagechat.services;

import com.example.sagechat.DTOs.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MessageServiceIml implements MessageService{

    String baseURL = "https://gfa-chat-broker.herokuapp.com/api";
    WebClient webClient = WebClient.create(baseURL);

    public void newMessage (NewMessage newMessage){

        Mono<NewMessageResponse> response = webClient.post()
                .uri("/message")
                .header("apiKey", UserServiceIml.apiKey)
                .body(BodyInserters.fromValue(newMessage))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(NewMessageResponse.class);
                    } else return Mono.just(new NewMessageResponse());
                });
        NewMessageResponse newMessageResponse = response.block();
        System.out.println(newMessageResponse);
    }

    public String newChannel (NewChannel newChannel){

        Mono<NewChannelResponse> response = webClient.post()
                .uri("/channel")
                .header("apiKey", UserServiceIml.apiKey)
                .body(BodyInserters.fromValue(newChannel))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(NewChannelResponse.class);
                    } else return Mono.just(new NewChannelResponse());
                });

        NewChannelResponse newChannelResponse = response.block();

        if ((newChannelResponse.getDescription()== null) && (newChannelResponse.getId() == null )){
            return null;
        } else {
            return newChannelResponse.getDescription() + " " + newChannelResponse.getName();
        }
    }

    public void updateChannel (UpdateChannel updateChannel){

        Mono<NewChannelResponse> response = webClient.post()
                .uri("/channel/update")
                .header("apiKey", UserServiceIml.apiKey)
                .body(BodyInserters.fromValue(updateChannel))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(NewChannelResponse.class);
                    } else return Mono.just(new NewChannelResponse());
                });

        response.block();
    }

    public List<NewChannelResponse> getUserChannels (){

        Mono<NewChannelResponse[]> response = webClient.get()
                .uri("/channel/user-channels")
                .header("apiKey", UserServiceIml.apiKey)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(NewChannelResponse[].class);
                    } else return Mono.just(null);
                });

        NewChannelResponse[] userChannelsArray = response.block();
        List<NewChannelResponse> userChannels = Arrays.asList(userChannelsArray);

        if ((userChannels.get(0).getDescription()== null) && (userChannels.get(0).getId() == null )){
            return null;
        } else {
            return userChannels;
        }
    }

    public List<MessagesOnChannel> getChannelMessages (ChatScreen chatScreen){

        Mono<ChatScreenResponse> response = webClient.post()
                .uri("/channel/get-messages")
                .header("apiKey", UserServiceIml.apiKey)
                .body(BodyInserters.fromValue(chatScreen))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(ChatScreenResponse.class);
                    } else return Mono.just(new ChatScreenResponse());
                });

        ChatScreenResponse chatScreenResponse = response.block();

        List<MessagesOnChannel> messagesOnChannel = new ArrayList<>();
        String messageContent = "";
        LocalDateTime dateF;
        String date;
        String author = "";
        for (int i = 0; i < chatScreenResponse.getMessages().size(); i++) {
            messageContent = chatScreenResponse.getMessages().get(i).getContent();
            author = chatScreenResponse.getMessages().get(i).getAuthor().getUsername();
            dateF = LocalDateTime.parse(chatScreenResponse.getMessages().get(i).getCreated());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            date = dtf.format(dateF);
            messagesOnChannel.add(new MessagesOnChannel(messageContent, date, author));
        }

        if ((chatScreenResponse.getMessages()== null) && (chatScreenResponse.getChannel()== null )){
            return null;
        } else {
            return messagesOnChannel;
        }
    }
}
