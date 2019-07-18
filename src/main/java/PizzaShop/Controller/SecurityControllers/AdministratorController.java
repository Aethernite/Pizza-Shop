package PizzaShop.Controller.SecurityControllers;

import PizzaShop.Entities.Order;
import PizzaShop.Entities.Product;
import PizzaShop.Models.UserDetails;
import PizzaShop.Repositories.ItemRepository;
import PizzaShop.Repositories.OrderRepository;
import PizzaShop.Repositories.ProductRepository;
import PizzaShop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@SessionAttributes({"UserDetails","email"})
@Controller
public class AdministratorController {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AdministratorController(ProductRepository productRepository, UserRepository userRepository, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/admin/home")
    public String home(Model model, RedirectAttributes redirectAttributes, Principal principal){
        UserDetails userDetails = this.userRepository.findOneByEmail(principal.getName());
        model.addAttribute("UserDetails", userDetails);
        model.addAttribute("email", userDetails.getEmail());
        redirectAttributes.addFlashAttribute("UserDetails",userDetails);
        redirectAttributes.addFlashAttribute("email",userDetails.getEmail());
        return "redirect:/admin/orders";
    }


    @GetMapping("/admin/orders")
    public ModelAndView orders(ModelAndView modelAndView){

        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/AdminViews/orders");
        List<Order> orders = this.orderRepository.findAllByStatus("not completed");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }


    @PostMapping("/admin/orders/complete/{id}")
    public String completeOrder(@PathVariable(value = "id") Integer id){
        Order order = this.orderRepository.findOneById(id);
        order.setStatus("completed");
        this.orderRepository.save(order);
        return "redirect:/admin/orders";
    }

    @GetMapping("/admin/product/status")
    public ModelAndView status(ModelAndView modelAndView){
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/AdminViews/status");
        List<Product> products = productRepository.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @PostMapping("/admin/product/disable/{id}")
    public String disable(@PathVariable(value ="id")Integer id){
        Product product = this.productRepository.findOneById(id);
        product.setStatus("disabled");
        this.productRepository.save(product);
        return "redirect:/admin/product/status";
    }

    @PostMapping("/admin/product/enable/{id}")
    public String enable(@PathVariable(value ="id")Integer id){
        Product product = this.productRepository.findOneById(id);
        product.setStatus("enabled");
        this.productRepository.save(product);
        return "redirect:/admin/product/status";
    }


    @GetMapping("/admin/product/add")
    public ModelAndView addProduct(ModelAndView modelAndView){
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", "views/AdminViews/addproduct");
        return modelAndView;
    }

    @PostMapping("/admin/product/add")
    public String addProduct(Product product){
        this.productRepository.saveAndFlush(product);
        return "redirect:/admin/product/add";
    }
}
