package br.com.caelum.clines.api.user;

import br.com.caelum.clines.shared.domain.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserViewMapperTest {

    private final String NAME = "usuario";
    private final String EMAIL = "usuario@email.com";
    private final String PASSWORD = "123456";

    private UserViewMapper mapper;

    @Test
    void shouldCovertUserToView() {
        User user = new User(NAME, EMAIL, PASSWORD);

        mapper = new UserViewMapper();

        UserView view = mapper.map(user);

        assertEquals(NAME, view.getName());
        assertEquals(EMAIL, view.getEmail());
    }
}
