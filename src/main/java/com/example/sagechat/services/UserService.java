package com.example.sagechat.services;

import com.example.sagechat.DTOs.Login;
import com.example.sagechat.DTOs.Register;
import com.example.sagechat.DTOs.Update;


public interface UserService {

    boolean isPostRegistrationInfoAndGetResponseSuccessful (Register register);
    boolean isLoginAndGetApikeySuccessful (Login login);
    void updateUser (Update update);
    void logout ();
}
