package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {
    @NotEmpty(message = "Please provide your email")
    private String email;
    @NotEmpty(message = "Please provide your password")
    private String password;
    private boolean remember;
}
