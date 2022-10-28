package com.example.sagechat.controllers;

import com.example.sagechat.DTOs.ChatScreen;
import com.example.sagechat.DTOs.NewChannel;
import com.example.sagechat.DTOs.NewMessage;
import com.example.sagechat.DTOs.UpdateChannel;
import com.example.sagechat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {

    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/newMessage")
    public String newMessage (){
        return "chatScreen";
    }

    @PostMapping("/newMessage")
    public String newMessage (@ModelAttribute NewMessage newMessage){
        if (newMessage.getChannelId()==null && newMessage.getChannelSecret().equals("")){
            newMessage.setChannelId(null);
            newMessage.setChannelSecret(null);
        }
        messageService.newMessage(newMessage);
        return "redirect:/chatScreen";
    }

    @GetMapping("/newChannel")
    public String newChannel (){
        return "chatScreen";
    }

    @PostMapping("/newChannel")
    @ResponseBody
    public String newChannel (@ModelAttribute NewChannel newChannel){
        return messageService.newChannel(newChannel);
    }

    @GetMapping("/updateChannel")
    public String updateChannel (Model model){
        model.addAttribute("userChannels", messageService.getUserChannels());
        return "updateChannel";
    }

    @PostMapping("/updateChannel")
    public String updateChannel (@ModelAttribute UpdateChannel updateChannel){
        messageService.updateChannel(updateChannel);
        return "redirect:/updateChannel";
    }


    @GetMapping("/chatScreen")
    public String chatScreen (Model model){
        model.addAttribute("messages", messageService.getChannelMessages(new ChatScreen()));
        return "chatScreen";
    }

    @GetMapping("/changeChannel")
    public String changeChannel (){
        return "chatScreen";
    }

    @PostMapping("/changeChannel")
    public String changeChannel (Model model, @RequestParam(required = false) Integer channelId,
                                              @RequestParam(required = false) String channelSecret ){
        ChatScreen chatScreen = new ChatScreen();
        if (channelId==null && channelSecret.equals("")){
            chatScreen.setChannelId(null);
            chatScreen.setChannelSecret(null);
        } else {
            chatScreen.setChannelId(channelId);
            chatScreen.setChannelSecret(channelSecret);
        }
        model.addAttribute("messages", messageService.getChannelMessages(chatScreen));
        return "chatScreen";
    }
}
