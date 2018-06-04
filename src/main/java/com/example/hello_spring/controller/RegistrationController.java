package com.example.hello_spring.controller;

import com.example.hello_spring.model.User;
import com.example.hello_spring.repository.UsersRepository;
import com.example.hello_spring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class RegistrationController {

    @Autowired
    EmailService emailService;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

/*
   (hellokoding)
   @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        System.out.print(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    (hellokoding)
    */
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/register")
    public String processRegistration(@ModelAttribute User user, ModelMap modelMap, HttpServletRequest request){
        user.setConfirmationToken(UUID.randomUUID().toString());
        usersRepository.save(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Registration Confirmation");

        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        mailMessage.setText("To confirm your e-mail address, please click the link below:\n"
            + appUrl + "/confirm?token=" + user.getConfirmationToken());
        mailMessage.setFrom("noreply@example.com");
        emailService.sendEmail(mailMessage);
        modelMap.addAttribute("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
        return "result";
    }

    @GetMapping("/confirm")
    public String showConfirmationPage(ModelMap modelMap, @RequestParam String token){
        User user = usersRepository.findByConfirmationToken(token);
        if (user == null){
            modelMap.addAttribute("confirmMessage", "Token is invalid");
            return "confirm";
        }

        user.setEnabled(true);
        usersRepository.save(user);
        modelMap.addAttribute("confirmMessage", "Your account is now active");
        return "confirm";
    }
}
