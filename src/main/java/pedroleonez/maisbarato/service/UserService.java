package pedroleonez.maisbarato.service;

import org.springframework.stereotype.Service;
import pedroleonez.maisbarato.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // a lógica fica aqui
}
