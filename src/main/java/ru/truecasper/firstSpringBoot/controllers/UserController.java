package ru.truecasper.firstSpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.truecasper.firstSpringBoot.models.User;
import ru.truecasper.firstSpringBoot.services.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/")
//    public String users(){
//        List<User> userList = userService.getUsers();
//        System.out.println(userList.size());
//        return "users";
//    }


    @GetMapping(value = "/")
    public String showUsers(ModelMap model){
        System.out.println(" --- startUser");
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "/index";
    }

    @GetMapping (value = "/form")
    public String newUserForm(ModelMap model){
        System.out.println(" --- newUser");
        model.addAttribute("user", new User());
        return "/form";
    }

    @PostMapping("/user")
    public String createUser(@ModelAttribute("user") User user){
        System.out.println(" --- create");
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(ModelMap model, @PathVariable("id") int id){
        System.out.println(" --- editUser");
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id){
        System.out.println(" --- updateUser");
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id){
        System.out.println(" --- deleteUser");
        userService.deleteUser(id);
        return "redirect:/";
    }
}
