package pedroleonez.maisbarato.services;

import org.springframework.stereotype.Service;
import pedroleonez.maisbarato.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
