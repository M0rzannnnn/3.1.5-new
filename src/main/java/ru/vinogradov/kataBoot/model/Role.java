package ru.vinogradov.kataBoot.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "name")
    private String name;

    @Column(name = "value")
    private String value;

//    public void setUsers(Collection<User> users) {
//        this.users = users;
//    }
//
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Collection<User> users;


    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Role() {
    }

//    public Collection<User> getUsers() {
//        return users;
//    }

    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
    @Override
    public String getAuthority() {
        return this.getName();
    }
}
