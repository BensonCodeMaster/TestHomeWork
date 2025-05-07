package web.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UsersController {


    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("userList", userService.getAll());
        return "users";
    }


    @PostMapping
    public  String addUser(@ModelAttribute("user")User user){
        userService.add(user);
        return  "redirect:/users";
    }

    @GetMapping("/user")  // URL теперь будет /user?id=123
    public String getUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    @GetMapping("/update")
    public String showUserUpdatePage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam Integer age) {
        userService.update(id, name, email, age);
        return "redirect:/users/" + id;
    }

    @GetMapping("/delete")
    public String showDeletePage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}


