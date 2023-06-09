package jpabook.jpashop.controller;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jpabook.jpashop.domain.consts.Country;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[$@!%*?&])[A-Za-z\\d$@$!%*?&]{8,10}$",
            message = "You must include one special character between 8 and 10 characters")
    private String password;
    @NotEmpty
    private String passwordCheck;
    @NotNull
    private Country country;
    @NotEmpty
    private String city;
    @NotEmpty
    private String street;
    @NotEmpty
    private String zip;
    @AssertTrue
    private boolean assertToTerms;
}
