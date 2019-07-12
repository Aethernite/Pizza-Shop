package PizzaShop.Controller;

import PizzaShop.Entities.Account;
import PizzaShop.Entities.Product;
import PizzaShop.Repositories.AccountRepository;

import java.util.List;

public class Utils {


    public static String convertListToString(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        for (Product pr : products) {
            sb.append(pr.getName() + " " + String.format("%.2f", pr.getPrice()) + "\n");
        }
        return sb.toString();
    }

    public static List<Product> removeFromCart(List<Product> cart, Integer id) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId().intValue() == id.intValue()) {
                cart.remove(i);
                break;
            }
        }
        return cart;
    }

    public static boolean isValid(Account account, AccountRepository accountRepository) {
        if (account.getUsername() == null || account.getPassword() == null || account.getUsername().trim().isEmpty() || account.getPassword().trim().isEmpty()) {
            return false;
        }
        Account acc = accountRepository.findByUsername(account.getUsername());
        if (acc != null) {
            return false;
        }
        Account email = accountRepository.findByEmail(account.getEmail());
        if (email != null) {
            return false;
        }
        return true;
    }

    public static double getTotal(List<Product> productList) {
        double total = 0;
        if (productList == null) {
            return total;
        }
        for (Product pr : productList) {
            total += pr.getPrice();
        }
        return total;
    }
}