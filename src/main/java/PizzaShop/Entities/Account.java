package PizzaShop.Entities;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    private Integer id;
    private String email;
    private String username;
    private String password;
    private String address;
    private String phone;
    private boolean admin;


    public Account(){

    }

    public Account(String email, String username, String password,String address, String phone, boolean admin) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.admin = false;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    @Column(name ="isAdmin", nullable = false)
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name ="email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "username",nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "address",nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "phone",nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
