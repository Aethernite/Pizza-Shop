package PizzaShop.Entities;



import javax.persistence.*;

@Entity
@Table(name ="items")
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    @Column(name ="quantity", nullable=false)
    private int quantity;
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    public Item(Product product, int quantity, Order order) {
        this.product = product;
        this.quantity = quantity;
        this.order = order;
    }

    public Item(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Item(){

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
