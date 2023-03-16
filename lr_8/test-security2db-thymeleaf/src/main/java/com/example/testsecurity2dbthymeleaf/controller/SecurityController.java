package com.example.testsecurity2dbthymeleaf.controller;

import com.example.testsecurity2dbthymeleaf.dto.UserDto;
import com.example.testsecurity2dbthymeleaf.entity.User;
import com.example.testsecurity2dbthymeleaf.entity.UserAction;
import com.example.testsecurity2dbthymeleaf.repository.UserActionRepository;
import com.example.testsecurity2dbthymeleaf.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SecurityController {

    private UserService userService;

    @Autowired
    private UserActionRepository userActionRepository;

    public SecurityController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(){
        userActionRepository.save( new UserAction(
                "User email - " + getUserMail() + ";" +
                        " Open index page."
        ));
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        userActionRepository.save( new UserAction(
                "User email - " + getUserMail() + ";" +
                        " Open login page."
        ));
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        userActionRepository.save( new UserAction(
                "User email - " + getUserMail() + ";" +
                        " Open register page."
        ));
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "На этот адрес электронной почты уже зарегистрирована учетая запись.");
            userActionRepository.save( new UserAction(
                    "User email - " + getUserMail() + ";" +
                            " Fail create user. " +
                            " UserEmail - " + userDto.getEmail()
            ));
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            userActionRepository.save( new UserAction(
                    "User email - " + getUserMail() + ";" +
                            " Error create user. " +
                            " UserEmail - " + userDto.getEmail() + ";"
            ));
            return "/register";
        }
        userService.saveUser(userDto);
        userActionRepository.save( new UserAction(
                "User email - " + getUserMail() + ";" +
                        " Create new user. " +
                        " UserID - " + userDto.getId() +
                        " UserEmail - " + userDto.getEmail() +
                        " UserFirst - " + userDto.getFirstName() +
                        " UserLast - " + userDto.getLastName() +
                        " UserPass - " + userDto.getPassword() + ";"
        ));
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        userActionRepository.save( new UserAction(
                "User email - " + getUserMail() + ";" +
                        " Open user list."
        ));
        return "users";
    }

    private String getUserMail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            return authentication.getName();
        }
        return "Unauthorizated user.";
    }
}
