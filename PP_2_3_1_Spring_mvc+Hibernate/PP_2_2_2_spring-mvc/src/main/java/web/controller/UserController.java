package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.DAO.UserDAO;
import web.DAO.UserDAOImpl;

@Controller
@RequestMapping(value = "users")
public class UserController {
    private UserDAO userDAO;

    public UserController(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }
}
