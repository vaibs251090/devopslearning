package com.devopslearning.backend.persistence.domain.backend;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    /** The Serial Version UID for Serializable classes. */
    private static final long serialVersionUID = 1L;

    public UserRole(){

    }

    public UserRole(User basicUser, Role role) {
        this.user=basicUser;
        this.role=role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id == userRole.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
