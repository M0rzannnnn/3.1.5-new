package ru.vinogradov.kataBoot.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public void setName(String name) {
        this.name = name;
    }
    @Column (name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role () {}

    public Role(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public String getName() {
        return name;
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
        return name;
    }
}
