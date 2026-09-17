package com.wisdom.Ecom_project.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

@Enabled
@Table(name = "users",
      uniqueConstraints = {
        @UniqueConstraint(columnNames = "email") // Ensures mathematical uniqueness of emails at the database layer
      })
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto generates your ids
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Email(message = "Please provide a valid email address")
    @Size(max = 60)
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(max = 120)
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 40)
    private String name;

    // Standard Default Constructor required by Hibernate to instantiate objects out of the database
    public Users() {}

    public Long getId() {
        return id;
    }

    public void setId(  Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                "name='" + name + '\'' +
                '}';
    }

}
