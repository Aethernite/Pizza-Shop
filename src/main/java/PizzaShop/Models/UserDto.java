package PizzaShop.Models;

import PizzaShop.Validators.PasswordMatches;
import PizzaShop.Validators.ValidEmail;
import PizzaShop.Validators.ValidPassword;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String mobile;

    @NotNull
    @NotEmpty
    private String address;



    // standard getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
