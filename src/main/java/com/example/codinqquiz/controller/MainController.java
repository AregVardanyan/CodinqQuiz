package com.example.codinqquiz.controller;

import com.example.codinqquiz.model.User;
import com.example.codinqquiz.security.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @GetMapping("/")
    public String home(Model modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof CurrentUser) {
                CurrentUser principal = (CurrentUser) authentication.getPrincipal();
                if (principal.getUser().isAdmin()) {
                    return "redirect:/admin/home";
                } else {
                    return "redirect:/user/home";

                }
            }
        }
        modelMap.addAttribute("registerUser", new User());
        modelMap.addAttribute("loginUser", new User());
        return "index";
    }



}
