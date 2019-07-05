package PizzaShop.Controller;

import PizzaShop.Entities.Account;
import PizzaShop.AccountRepository.AccountRepository;
import PizzaShop.Entities.Product;
import PizzaShop.ProductRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private Account logged = null;
    @Autowired
    public Controller(AccountRepository accountRepository, ProductRepository productRepository) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
    }


    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        logged = null;
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/index");
        return modelAndView;
    }

    @PostMapping("/")
    public String login(Account account){
        if (checkAccount(account)) return "redirect:/";
        List<Account> accounts = this.accountRepository.findAll();
        Account acc = accounts.stream()
                .filter(account1 -> account.getUsername().equals(account1.getUsername()))
                .findAny()
                .orElse(null);
        if(acc.getUsername().equals(account.getUsername()) && acc.getPassword().equals(account.getPassword())){
            logged = acc;
            if(acc.isAdmin()) {
                return "redirect:/pizzasAdmin";
            }
            return "redirect:/pizzas";
        }
            return "redirect:/";
    }

    private boolean checkAccount(Account account) {
        if(account.getUsername() == null || account.getPassword() == null || account.getUsername().trim().isEmpty() || account.getPassword().trim().isEmpty()){
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
        if (checkAccount(account)) return "redirect:/register";
        accountRepository.saveAndFlush(account);
        return "redirect:/";
    }

    @GetMapping("/pizzas")
    public ModelAndView pizzas(ModelAndView modelAndView){
        List<Product> products = this.productRepository.findAllByType("pizza");
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/pizzas");
        modelAndView.addObject("products", products);
        modelAndView.addObject("account", logged);
        return modelAndView;
    }

   @GetMapping("/drinks")
    public ModelAndView drinks(ModelAndView modelAndView){
        List<Product> products = this.productRepository.findAllByType("drink");
       modelAndView.setViewName("base-layout");
       modelAndView.addObject("view", "views/drinks");
       modelAndView.addObject("products", products);
       modelAndView.addObject("account", logged);
       return modelAndView;
   }

   @GetMapping("/pizzasAdmin")
   public ModelAndView pizzasAdmin(ModelAndView modelAndView){
       List<Product> products = this.productRepository.findAllByType("pizza");
       modelAndView.setViewName("base-layout");
       modelAndView.addObject("view", "views/pizzasAdmin");
       modelAndView.addObject("products", products);
       modelAndView.addObject("account", logged);
       return modelAndView;
   }

    @GetMapping("/drinksAdmin")
    public ModelAndView drinksAdmin(ModelAndView modelAndView){
        List<Product> products = this.productRepository.findAllByType("drink");
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/drinksAdmin");
        modelAndView.addObject("products", products);
        modelAndView.addObject("account", logged);
        return modelAndView;
    }


}
