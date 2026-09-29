package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/get")
    public String getUserById(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user, Model model) {
        userService.saveUser(user.getName(), user.getMiddleName(), user.getSurName(), user.getMail());
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, Model model) {
        userService.updateUser(user.getId(), user.getName(), user.getMiddleName(), user.getSurName(), user.getMail());
        return "redirect:/users";
    }
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Controller works!";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        try {
            userService.removeUserById(id);
            return "redirect:/users";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка удаления пользователя: " + e.getMessage());
            return "error";
        }
    }

}