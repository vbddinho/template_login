package com.ridolphiconsutoria.timesheetv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping()
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping({ "/", "/login" } )
    public String formLoginPage(Model model,@RequestParam(value = "erro", required = false) String erro) {

        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        
        if(!(autenticado instanceof AnonymousAuthenticationToken)){
            
            return "redirect:/home";
        
        }

        if (erro != null) {
            model.addAttribute("errorMessage", "Credenciais inválidas. Por favor, tente novamente.");
        }

        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Redireciona para a página desejada após a autenticação
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";

    }


    @GetMapping("/login/esqueci")
    public String pageEsqueci(){

        return "/esqueci";
    }
}
