package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.sql.SQLException;
import java.util.List;

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
        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "users";
        } catch (SQLException e) {
            model.addAttribute("error", "Error loading users: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        try {
            List<User> users = userService.getAllUsers();
            User user = users.stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (user == null) {
                model.addAttribute("error", "User not found");
                return "error";
            }
            model.addAttribute("user", user);
            return "user-details";
        } catch (SQLException e) {
            model.addAttribute("error", "Error loading user: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user, Model model) {
        try {
            userService.saveUser(user.getName(), user.getMiddleName(), user.getSurName(), user.getMail());
            return "redirect:/users";
        } catch (SQLException e) {
            model.addAttribute("error", "Error creating user: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            List<User> users = userService.getAllUsers();
            User user = users.stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (user == null) {
                model.addAttribute("error", "User not found");
                return "error";
            }
            model.addAttribute("user", user);
            return "user-edit";
        } catch (SQLException e) {
            model.addAttribute("error", "Error loading user: " + e.getMessage());
            return "error";
        }
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user, Model model) {
        try {
            List<User> users = userService.getAllUsers();
            User existingUser = users.stream()
                    .filter(u -> u.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (existingUser == null) {
                model.addAttribute("error", "User not found");
                return "error";
            }
            userService.removeUserById(id);
            userService.saveUser(user.getName(), user.getMiddleName(), user.getSurName(), user.getMail());
            return "redirect:/users";
        } catch (SQLException e) {
            model.addAttribute("error", "Error updating user: " + e.getMessage());
            return "error";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        try {
            userService.removeUserById(id);
            return "redirect:/users";
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting user: " + e.getMessage());
            return "error";
        }
    }
}