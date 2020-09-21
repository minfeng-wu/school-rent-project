package com.ascending.training.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "school")
public class School {
    public School(){}
    public School(String name, String city, String state, String address){
        this.name = name;
        this.city = city;
        this.state = state;
        this.address = address;
    }




    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Account> accounts;

    public void addAccount(Account account){
        this.getAcounts().add(account);
        account.setSchool(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Account> getAcounts() {
        if(accounts == null){
            accounts = new HashSet<Account>();
        }
        return accounts;
    }

    public void setAcounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
