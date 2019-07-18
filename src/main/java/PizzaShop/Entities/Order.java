package PizzaShop.Entities;

import PizzaShop.Models.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user_id;
    @Column(name = "status",nullable = false)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time", nullable = false)
    private Date dateTime;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id")
    private Set<Item> items;

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Order(){

    }

    public Order(User user_id) {
        this.user_id = user_id;
        this.status = "not completed";
        this.dateTime = new Date();
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
