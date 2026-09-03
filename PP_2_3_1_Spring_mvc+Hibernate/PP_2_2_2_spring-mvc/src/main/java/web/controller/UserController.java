package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.DAO.UserDAO;
import web.DAO.UserDAOImpl;
import web.service.CarService;
import web.service.CarServiceImpl;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final CarServiceImpl carService;
    private UserDAO userDAO;
    @Autowired
    public UserController(UserDAOImpl userDAO, CarServiceImpl carService) {
        this.userDAO = userDAO;
        this.carService = carService;
    }

    @GetMapping()
    public String count(@RequestParam(value = "count", required = false) Integer count, Model model) {
        model.addAttribute("cars", carService.count(count));
        return "cars";
    }
}
