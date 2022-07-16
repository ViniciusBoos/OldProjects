package br.com.caelum.clines.api.user;

import br.com.caelum.clines.shared.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFormMapperTest {

    private final String NAME = "usuario";
    private final String EMAIL = "usuario@email.com";
    private final String PASSWORD = "123456";

    private UserFormMapper userFormMapper = new UserFormMapper();

    @Test
    void shouldReturnAUserIfAUserFormIsCorrect() {
        var userForm = new UserForm(NAME, EMAIL, PASSWORD);

        var user = userFormMapper.map(userForm);

        assertEquals(user.getName(), NAME);
        assertEquals(user.getEmail(), EMAIL);
        assertEquals(user.getPassword(), PASSWORD);
    }
}
