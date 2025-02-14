package pedroleonez.maisbarato.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pedroleonez.maisbarato.model.UserModel;
import pedroleonez.maisbarato.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // metodo para registrar um novo usuário
    public UserModel register(UserModel user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // metodo para consultar o usuário logado
    public Optional<UserModel> getCurrentUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email);
    }

    // metodo para resetar senha
    public void resetPassword(UUID userId, String newPassword) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // metodo para atualizar o nome do usuário
    public UserModel updateName(String newName) {
        // obtém o email do usuário autenticado
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // busca o usuário no banco de dados
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // atualiza o nome
        user.setName(newName);

        // salva o usuário atualizado no banco de dados
        return userRepository.save(user);
    }
}
