package br.com.caelum.clines.api.user;

import br.com.caelum.clines.api.aircraftmodels.AircraftModelService;
import br.com.caelum.clines.shared.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final String NAME = "usuario";
    private final String EMAIL = "usuario@email.com";
    private final String PASSWORD = "123456";
    private final User USER = new User(NAME, EMAIL, PASSWORD);
    private final List<User> USERS = List.of(USER);

    @Mock
    private UserRepository repository;

    @Spy
    private UserViewMapper viewMapper;

    @Spy
    private UserFormMapper formMapper;

    @InjectMocks
    private UserService service;


    @Test
    void shouldReturnAListOfUsers(){
        given(repository.findAll()).willReturn(USERS);

        var allUserViews = service.findAll();

        then(repository).should(only()).findAll();
        then(viewMapper).should(only()).map(USER);
        then(formMapper).shouldHaveNoInteractions();

        assertEquals(USERS.size(), allUserViews.size());

        var UserView = allUserViews.get(0);

        assertEquals(NAME, UserView.getName());
        assertEquals(EMAIL, UserView.getEmail());
    }

    @Test
    void shouldReturnUserViewIfIdExist(){


    }
}
