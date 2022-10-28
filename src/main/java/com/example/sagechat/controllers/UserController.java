package com.example.sagechat.controllers;

import com.example.sagechat.DTOs.Login;
import com.example.sagechat.DTOs.Register;
import com.example.sagechat.DTOs.Update;
import com.example.sagechat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String redirect (){
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register (){
        return "register";
    }

    @PostMapping("/register")
    public String register (Model model, @ModelAttribute Register register){
        if (userService.isPostRegistrationInfoAndGetResponseSuccessful(register)){
            model.addAttribute("success", 0);
            return "login";
        } else {
            model.addAttribute("error", 0);
            return "register";
        }
    }

    @GetMapping("/login")
    public String login (){
        return "login";
    }

    @PostMapping("/login")
    public String login (Model model, @ModelAttribute Login login){
        if (userService.isLoginAndGetApikeySuccessful(login)){
            return "redirect:/chatScreen";
        } else {
            model.addAttribute("error", 0);
            return "login";
        }
    }

    @GetMapping("/update")
    public String update (){
        return "update";
    }

    @PostMapping("/update")
    public String update (@ModelAttribute Update update){
        userService.updateUser(update);
        return "redirect:/chatScreen";
    }

    @GetMapping("/logout")
    public String logoutGet (){
        userService.logout();
        return "redirect:/login";
    }


}
