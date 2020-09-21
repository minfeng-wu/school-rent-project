package com.ascending.training.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    public Role(){}
    public Role(long id, String name, String allowedResource, boolean allowedRead, boolean allowedCreate, boolean allowedUpdate, boolean allowedDelete) {
        this.id = id;
        this.name = name;
        this.allowedResource = allowedResource;
        this.allowedRead = allowedRead;
        this.allowedCreate = allowedCreate;
        this.allowedUpdate = allowedUpdate;
        this.allowedDelete = allowedDelete;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "allowed_resource")
    private String allowedResource;

    @Column(name = "allowed_read")
    private boolean allowedRead;

    @Column(name = "allowed_create")
    private boolean allowedCreate;

    @Column(name = "allowed_update")
    private boolean allowedUpdate;

    @Column(name = "allowed_delete")
    private boolean allowedDelete;

    //Not owning side should have mappedBy
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<User> users;

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

    public String getAllowedResource() {
        return allowedResource;
    }

    public void setAllowedResource(String allowedResource) {
        this.allowedResource = allowedResource;
    }

    public boolean isAllowedRead() {
        return allowedRead;
    }

    public void setAllowedRead(boolean allowedRead) {
        this.allowedRead = allowedRead;
    }

    public boolean isAllowedCreate() {
        return allowedCreate;
    }

    public void setAllowedCreate(boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }

    public boolean isAllowedUpdate() {
        return allowedUpdate;
    }

    public void setAllowedUpdate(boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }

    public boolean isAllowedDelete() {
        return allowedDelete;
    }

    public void setAllowedDelete(boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
    }

    public Set<User> getUsers() {
        if(users == null)
            users = new HashSet<User>();
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        this.getUsers().add(user);
        user.getRoles().add(this);
    }

    public void removeUser(User user){
        this.getUsers().remove(user);
        user.getRoles().remove(this);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", allowedResource='" + allowedResource + '\'' +
                ", allowedRead=" + allowedRead +
                ", allowedCreate=" + allowedCreate +
                ", allowedUpdate=" + allowedUpdate +
                ", allowedDelete=" + allowedDelete +
                '}';
    }
}
