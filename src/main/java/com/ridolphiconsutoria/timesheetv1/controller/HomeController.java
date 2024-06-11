package com.ridolphiconsutoria.timesheetv1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ridolphiconsutoria.timesheetv1.usuario.UserModel;
import com.ridolphiconsutoria.timesheetv1.usuario.UserRepository;



@Controller
public class HomeController {


    @Autowired UserRepository userRepository;


    @GetMapping("/home")
    public String homePage(Model model){

         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth.isAuthenticated()){
            UserModel user = userRepository.findByUsername(auth.getName()).get();
            
            System.out.println(auth.getName());
            
            
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("Nome", user.getNome());
            return "home";
        }
        return "login";
    }
    
}
