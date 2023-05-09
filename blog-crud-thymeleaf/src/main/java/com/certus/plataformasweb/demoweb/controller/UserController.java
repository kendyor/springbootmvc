package com.certus.plataformasweb.demoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.certus.plataformasweb.demoweb.model.UserCertus;
import com.certus.plataformasweb.demoweb.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class UserController {
	//camelCase
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/signup")
	public String showSignUpForm(UserCertus user, Model model) {
        model.addAttribute("user", user);
		return "add-user";
	}
	
    @PostMapping("/adduser")
	public String addUser(@Valid UserCertus user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-user";
		}
		
		userRepository.save(user);
		
	    return "redirect:/index";
	}
    
    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	UserCertus user = userRepository.findById(Long.getLong(id+"")).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        model.addAttribute("user", user);
        
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid UserCertus user, 
      BindingResult result, Model model) {
    	
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
            
        userRepository.save(user);
        
        return "redirect:/index";
    }
        
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
    	UserCertus user = userRepository.findById(Long.getLong(id+"")).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        userRepository.delete(user);
        
        return "redirect:/index";
    }
    
}
