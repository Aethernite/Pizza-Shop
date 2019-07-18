package PizzaShop.Services;

import PizzaShop.Exceptions.EmailExistsException;
import PizzaShop.Models.User;
import PizzaShop.Models.UserDto;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;
}