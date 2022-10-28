package com.example.sagechat.services;

import com.example.sagechat.DTOs.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface MessageService {

    void newMessage (NewMessage newMessage);
    String newChannel (NewChannel newChannel);
    void updateChannel (UpdateChannel updateChannel);
    List<NewChannelResponse> getUserChannels ();
    List<MessagesOnChannel> getChannelMessages (ChatScreen chatScreen);
}
