package br.com.caelum.clines.api.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserForm {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

    public UserForm(String name, String email, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
