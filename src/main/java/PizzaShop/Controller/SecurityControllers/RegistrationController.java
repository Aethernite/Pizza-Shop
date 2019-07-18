package PizzaShop.Controller.SecurityControllers;

import PizzaShop.Exceptions.EmailExistsException;
import PizzaShop.Models.User;
import PizzaShop.Models.UserDto;
import PizzaShop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService service;

    @Autowired
    public RegistrationController(UserService service){
        this.service = service;
    }


    @GetMapping("/register")
    public ModelAndView showRegistrationForm(WebRequest request, ModelAndView modelAndView) {
        UserDto userDto = new UserDto();
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/register");
        modelAndView.addObject("user", userDto);
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result, WebRequest request, Errors errors) {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError", "User with that email already exists!");
        }
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("base-layout");
            modelAndView.addObject("view", "/views/register");
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("base-layout");
            modelAndView.addObject("view", "/views/index");
            return modelAndView;
        }
    }
    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}
