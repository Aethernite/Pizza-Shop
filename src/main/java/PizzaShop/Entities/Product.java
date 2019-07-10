package PizzaShop.Entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    private Integer id;
    private String imgurl;
    private String name;
    private String description;
    private float price;
    private String type;
    private String status;


    public Product(){

    }

    public Product(String imgurl, String name, String description, float price, String type, String status) {
        this.imgurl = imgurl;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name ="imgurl", nullable = false)
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Column(name ="name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name ="description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name ="price", nullable = false)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    @Column(name ="type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(name ="status", nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
