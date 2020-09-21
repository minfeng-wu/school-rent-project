package com.ascending.training.model;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {
    public Item(){}
    public Item(String name, boolean avaliability, String brand, String category, BigDecimal price, Set<Order> orders) {
        this.name = name;
        this.avaliability = avaliability;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.orders = orders;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "avaliability")
    private boolean avaliability;

    @Column(name = "brand")
    private String brand;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Order> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAvaliability() {
        return avaliability;
    }

    public void setAvaliability(boolean avaliability) {
        this.avaliability = avaliability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        if(orders == null){
            orders = new HashSet<Order>();
        }
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        this.getOrders().add(order);
        order.setItem(this);
    }
}
