package PizzaShop.Controller;

import PizzaShop.Entities.Account;
import PizzaShop.AccountRepository.AccountRepository;
import PizzaShop.Entities.Product;
import PizzaShop.ProductRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
@SessionAttributes({"account","cart"})
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
    public String login(Account account, RedirectAttributes redirectAttributes){

        if (isValid(account)) return "redirect:/";
        List<Account> accounts = this.accountRepository.findAll();
        Account acc = accounts.stream()
                .filter(account1 -> account.getUsername().equals(account1.getUsername()))
                .findAny()
                .orElse(null);
        if(acc.getUsername().equals(account.getUsername()) && acc.getPassword().equals(account.getPassword())){
            redirectAttributes.addFlashAttribute("account", acc);
            redirectAttributes.addFlashAttribute("cart", new ArrayList<Product>());
            if(acc.isAdmin()){
                return "redirect:/orders";
            }
            return "redirect:/pizzas";
        }
            return "redirect:/";
    }


    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView){
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/register");
        return modelAndView;
    }

    @PostMapping("/register")
    public String create(Account account){
        if (isValid(account)) return "redirect:/register";
        accountRepository.saveAndFlush(account);
        return "redirect:/";
    }

    @GetMapping("/pizzas")
    public ModelAndView pizzas(ModelAndView modelAndView){
        List<Product> products = this.productRepository.findAllByType("pizza");
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("products", products);
        modelAndView.addObject("view", "views/pizzas");
        return modelAndView;
    }

   @GetMapping("/drinks")
    public ModelAndView drinks(ModelAndView modelAndView){
        List<Product> products = this.productRepository.findAllByType("drink");
       modelAndView.setViewName("base-layout");
       modelAndView.addObject("view", "views/drinks");
       modelAndView.addObject("products", products);
       modelAndView.addObject("view", "views/drinks");
       return modelAndView;
   }


    @GetMapping("/cart")
    public ModelAndView cart(ModelAndView modelAndView,@ModelAttribute("cart") List<Product> cart){
        double total = getTotal(cart);
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/cart");
        modelAndView.addObject("total", total);
        return modelAndView;
    }


    @GetMapping("/add/{id}")
    public ModelAndView add(ModelAndView modelAndView, @PathVariable(value = "id") Integer id, @ModelAttribute("cart") List<Product> cart, RedirectAttributes redirectAttributes){
        Product product = this.productRepository.findById(id).get();
        cart.add(product);
        redirectAttributes.addFlashAttribute("cart", cart);
        double total = getTotal(cart);
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/cart");
        modelAndView.addObject("total", total);
        return modelAndView;
    }



    @GetMapping("/remove/{id}")
    public ModelAndView remove(ModelAndView modelAndView, @PathVariable(value="id") Integer id, @ModelAttribute("cart") List<Product> cart, RedirectAttributes redirectAttributes){
        cart = removeFromCart(cart,id);
        redirectAttributes.addFlashAttribute("cart", cart);
        double total = getTotal(cart);
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/cart");
        modelAndView.addObject("total", total);
        return modelAndView;
    }


    private List<Product> removeFromCart(List<Product> cart, Integer id){
        for(int i=0; i<cart.size(); i++){
           if(cart.get(i).getId().intValue() == id.intValue()){
               cart.remove(i);
               break;
           }
        }
        return cart;
    }

    private boolean isValid(Account account) {
        if(account.getUsername() == null || account.getPassword() == null || account.getUsername().trim().isEmpty() || account.getPassword().trim().isEmpty()){
            return true;
        }
        return false;
    }

    private double getTotal(List<Product> productList){
        double total = 0;
        if(productList==null){
            return total;
        }
        for(Product pr: productList){
            total+=pr.getPrice();
        }
        return total;
    }
}
