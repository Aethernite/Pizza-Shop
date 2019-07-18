package PizzaShop.Controller.SecurityControllers;

import PizzaShop.Controller.Utils;
import PizzaShop.Entities.Item;
import PizzaShop.Entities.Order;
import PizzaShop.Entities.Product;
import PizzaShop.Models.User;
import PizzaShop.Models.UserDetails;
import PizzaShop.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"cart","UserDetails","email"})
@Controller
public class CustomerController {
private final ProductRepository productRepository;
private final UserRepository userRepository;
private final ItemRepository itemRepository;
private final OrderRepository orderRepository;

    @Autowired
    public CustomerController(ProductRepository productRepository, UserRepository userRepository, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }



    @GetMapping("/customer/home")
    public String home(Model model, RedirectAttributes redirectAttributes, Principal principal){
        List<Item> cart = new ArrayList<Item>();
        model.addAttribute("cart", cart);
        UserDetails userDetails = this.userRepository.findOneByEmail(principal.getName());
        model.addAttribute("UserDetails", userDetails);
        model.addAttribute("email", userDetails.getEmail());
        redirectAttributes.addFlashAttribute(cart);
        redirectAttributes.addFlashAttribute("UserDetails",userDetails);
        redirectAttributes.addFlashAttribute("email",userDetails.getEmail());
        return "redirect:/customer/pizzas";
    }

    @GetMapping("/customer/pizzas")
    public ModelAndView pizzas(ModelAndView modelAndView){
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/CustomerViews/pizzas");
        List<Product> products = productRepository.findAllByTypeAndStatus("pizza","enabled");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/customer/drinks")
    public ModelAndView drinks(ModelAndView modelAndView){
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/CustomerViews/drinks");
        List<Product> products = productRepository.findAllByTypeAndStatus("drink","enabled");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/customer/cart")
    public ModelAndView cart(ModelAndView modelAndView, @ModelAttribute("cart") List<Item> cart){
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/CustomerViews/cart");
        modelAndView.addObject("cart", cart);
        modelAndView.addObject("total", Utils.getTotal(cart));
        return modelAndView;
    }

    @PostMapping("/customer/cart/add/{id}")
    public String add(@PathVariable(value = "id") Integer id, Authentication authentication, @ModelAttribute("cart") List<Item> cart, RedirectAttributes redirectAttributes){
        Product product = this.productRepository.findById(id).get();
        Item item = new Item(product,1);
        cart = Utils.addToCart(cart, item);
        redirectAttributes.addFlashAttribute("cart", cart);
        return "redirect:/customer/cart";
    }

    @PostMapping("/customer/cart/remove/{id}")
    public String remove( @PathVariable(value="id") Integer id, @ModelAttribute("cart") List<Item> cart, RedirectAttributes redirectAttributes){
        Product product = this.productRepository.findById(id).get();
        cart = Utils.removeFromCart(cart, product);
        redirectAttributes.addFlashAttribute("cart", cart);
        return "redirect:/customer/cart";
    }

    @PostMapping("/customer/order")
    public String createOrder(@ModelAttribute("cart") List<Item> cart,@ModelAttribute("UserDetails") UserDetails userDetails,RedirectAttributes redirectAttributes){
        Order order = new Order((User)userDetails);
        this.orderRepository.saveAndFlush(order);
        for(Item item: cart){
          item.setOrder(order);
          this.itemRepository.saveAndFlush(item);
        }
        cart.clear();
        redirectAttributes.addFlashAttribute("cart", cart);
        return "redirect:/customer/cart";
    }

}
