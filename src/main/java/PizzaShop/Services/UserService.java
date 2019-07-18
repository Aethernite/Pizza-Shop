package PizzaShop.Services;

import PizzaShop.Exceptions.EmailExistsException;
import PizzaShop.Models.Role;
import PizzaShop.Models.User;
import PizzaShop.Models.UserDto;
import PizzaShop.Repositories.RoleRepository;
import PizzaShop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements IUserService {
    private UserRepository repository;
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.repository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
        if (emailExists(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address:"  + accountDto.getEmail());
        }
        User user = new User();
        user.setName(accountDto.getName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setAddress(accountDto.getAddress());
        user.setMobile(accountDto.getMobile());
        user.setEmail(accountDto.getEmail());
        user.setStatus("VERIFIED");
        Set<Role> set = new HashSet<>();
        set.add(roleRepository.findOneByRole("SITE_USER"));
        user.setRoles(set);
        return repository.save(user);
    }
    private boolean emailExists(String email) {
        User user = repository.findOneByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

}