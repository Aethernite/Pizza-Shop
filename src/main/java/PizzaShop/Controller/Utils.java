package PizzaShop.Controller;



import PizzaShop.Entities.Item;
import PizzaShop.Entities.Product;

import java.util.List;

public class Utils {


    public static double getTotal(List<Item> cart){
        double total = 0;
        for(int i=0; i<cart.size();i++){
            Item item = cart.get(i);
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public static List<Item> addToCart(List<Item> cart, Item item){
        Product product = item.getProduct();
        for(int i = 0; i<cart.size(); i++){
            if(cart.get(i).getProduct().getId().equals(product.getId())){
                cart.get(i).setQuantity(cart.get(i).getQuantity() + 1);
                return cart;
            }
        }
        cart.add(item);
        return cart;
    }

    public static List<Item> removeFromCart(List<Item> cart, Product product){
        for(int i=0; i<cart.size(); i++){
            Item item = cart.get(i);
            if(item.getProduct().getId().equals(product.getId())){
                if(item.getQuantity() == 1){
                    cart.remove(i);
                    break;
                }else{
                    cart.get(i).setQuantity(item.getQuantity()-1);
                    break;
                }
            }
        }
        return cart;
    }

}