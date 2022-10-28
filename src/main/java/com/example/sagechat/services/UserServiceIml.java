package com.example.sagechat.services;

import com.example.sagechat.DTOs.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServiceIml implements UserService{

    static String apiKey;
    String baseURL = "https://gfa-chat-broker.herokuapp.com/api/user";
    WebClient webClient = WebClient.create(baseURL);

    public boolean isPostRegistrationInfoAndGetResponseSuccessful (Register register){

        Mono<RegisterResponse> response = webClient.post()
                .uri("/register")
                .body(BodyInserters.fromValue(register))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(RegisterResponse.class);
                    } else return Mono.just(new RegisterResponse());
                });

        RegisterResponse registerResponse = response.block();

        if ((registerResponse.getUserId() == null) && (registerResponse.getUsername() == null )){
            return false;
        } else return true;
    }

    public boolean isLoginAndGetApikeySuccessful (Login login){

        Mono<LoginResponse> response = webClient.post()
                .uri("/login")
                .body(BodyInserters.fromValue(login))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(LoginResponse.class);
                    } else return Mono.just(new LoginResponse());
                });

        LoginResponse loginResponse = response.block();

        if ((loginResponse.getUsername() == null) && (loginResponse.getApiKey() == null )){
            return false;
        } else {
            apiKey = loginResponse.getApiKey();
            return true;
        }
    }

    public void updateUser (Update update){

        Mono<UpdateResponse> response = webClient.post()
                .uri("/update")
                .header("apiKey", apiKey)
                .body(BodyInserters.fromValue(update))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(UpdateResponse.class);
                    } else return Mono.just(new UpdateResponse());
                });

        response.block();
    }

    public void logout (){
        Mono<Boolean> response = webClient.post().uri("/logout")
                .header("apiKey", apiKey)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(Boolean.class);
                    } else return Mono.just(null);
                });
        response.block();
        apiKey = "";
    }

}
