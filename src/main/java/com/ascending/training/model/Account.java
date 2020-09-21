package com.ascending.training.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {
    public Account(){}
    public Account(School school, String firstName, String lastName, String address,String accountName,String password, BigDecimal balance) {
        this.school = school;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.accountName = accountName;
        this.password = password;
        this.balance = balance;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;

    // @Column(name = "school_id")
    // private long schoolId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "password")
    private String password;


    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Order> orders;

    public void addOrder(Order order){
        this.getOrders().add(order);
        order.setAccount(this);
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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





}
