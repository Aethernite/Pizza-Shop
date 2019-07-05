package PizzaShop.Controller;

import PizzaShop.Entities.Account;
import PizzaShop.AccountRepository.AccountRepository;
import PizzaShop.Entities.Product;
import PizzaShop.ProductRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    @Autowired
    public Controller(AccountRepository accountRepository, ProductRepository productRepository) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
    }


    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/index");
        return modelAndView;
    }

    @PostMapping("/")
    public String login(Account account){
        if (checkLogin(account)) return "redirect:/";
        List<Account> accounts = this.accountRepository.findAll();
        Account acc = accounts.stream()
                .filter(account1 -> account.getUsername().equals(account1.getUsername()))
                .findAny()
                .orElse(null);
        if(acc.getUsername().equals(account.getUsername()) && acc.getPassword().equals(account.getPassword())){
            return "redirect:/pizzas";
        }
            return "redirect:/";
    }

    private boolean checkLogin(Account account) {
        if(account.getUsername() == null || account.getPassword() == null){
            return true;
        }
        return false;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView){
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/register");
        return modelAndView;
    }

    @PostMapping("/register")
    public String create(Account account){
        accountRepository.saveAndFlush(account);
        return "redirect:/";
    }

    @GetMapping("/pizzas")
    public ModelAndView homepage(ModelAndView modelAndView){
        List<Product> products = this.productRepository.findAllByType("pizza");
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/pizzas");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

   @GetMapping("/drinks")
    public ModelAndView drinks(ModelAndView modelAndView){
        List<Product> products = this.productRepository.findAllByType("drink");
       modelAndView.setViewName("base-layout");
       modelAndView.addObject("view", "views/drinks");
       modelAndView.addObject("products", products);
       return modelAndView;
   }
}
