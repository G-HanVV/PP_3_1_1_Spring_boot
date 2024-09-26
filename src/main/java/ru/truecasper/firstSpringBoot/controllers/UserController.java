package ru.truecasper.firstSpringBoot.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.truecasper.firstSpringBoot.models.User;
import ru.truecasper.firstSpringBoot.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showUsers(ModelMap model){
        logger.info("Showing users");
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "/index";
    }

    @GetMapping (value = "/form")
    public String newUserForm(ModelMap model){
        logger.info("New user form");
        model.addAttribute("user", new User());
        return "/form";
    }

    @PostMapping("/users")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model){
        logger.info("Creating new user");
        if (result.hasErrors()) {
            logger.info("Validation errors");
            List<String> strings = new ArrayList<>();
            result.getAllErrors().forEach(error -> strings.add(error.getDefaultMessage()));
            model.addAttribute("errors", strings);
            return "/form";
        }
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(ModelMap model, @PathVariable("id") int id){
        logger.info("Editing user");
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PutMapping("/{id}")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result, @PathVariable("id") int id){
        logger.info("Updating user {}", user.getName());
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id){
        logger.info("Deleting user");
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
