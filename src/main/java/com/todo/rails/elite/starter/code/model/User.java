package com.todo.rails.elite.starter.code.model;

import jakarta.persistence.*;

// TODO 5 Add the @Column annotation on the field and set the column name as roles and nullable as false.
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Column specifies the mapping of this field to a database column.
    // name = "column_name" sets the name of the column in the table.
    // nullable = false/true indicates whether the column can store null values.
    // Replace DataType with the field’s type (e.g., String, Integer) and fieldName with your variable name.
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "roles", nullable = false)
    private String roles;

    public User() {
    }

    public User(String username, String password, String email, String roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
