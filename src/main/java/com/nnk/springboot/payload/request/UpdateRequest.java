package com.nnk.springboot.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UpdateRequest {

    @NotBlank(message = "Fullname is mandatory")
    private String fullname;

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "email is mandatory")
    @Email
    private String email;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
}
