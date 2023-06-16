package com.mm.com.security.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class CreateUserDto {
    @NotBlank(message = "Tu usuario es requerido")
    private String username;
    @NotBlank(message = "Tu correo es requerido")
    @Email(message = "Correo electronico invalido invalido")
    private String email;
    @NotBlank(message = "Tu contraseña es requerido")
    private String password;

    @NotEmpty(message = "Tu rol es obligatorio")
    List<String> roles = new ArrayList<>();

    public CreateUserDto() {
    }

    public CreateUserDto(String username, String email, String password, List<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
