package br.com.caelum.clines.api.user;

import br.com.caelum.clines.shared.domain.User;
import br.com.caelum.clines.shared.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserViewMapper viewMapper;
    private final UserFormMapper userFormMapper;

    public List<UserView> findAll() {
        return repository.findAll().stream().map(viewMapper::map).collect(toList());
    }

    public UserView showUserBy(Long id) {

        Optional<User> user = repository.findById(id);

        if(user.isPresent()) {

            return viewMapper.map(user.get());
        } else {
            throw new ResourceNotFoundException("Não foi possivel encontrar esse usuario");
        }

    }

    public Long createUserBy(UserForm form) {

        User user = userFormMapper.map(form);

        repository.save(user);

        return user.getId();
    }
}
