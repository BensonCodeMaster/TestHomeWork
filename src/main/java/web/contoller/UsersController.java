package web.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

@Controller
@RequestMapping(value = "/users")
public class UsersController {


    private final UserService userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "users";
    }


    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";  // Перенаправляем обратно на список пользователей
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    @GetMapping("/update/{id}")
    public String showUserUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             @RequestParam("age") Integer age) {
        userService.update(id, name, surname, age);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String showDeletePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "delete";

    }
}


