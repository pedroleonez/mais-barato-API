package pedroleonez.maisbarato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedroleonez.maisbarato.domain.model.UserModel;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String attr0);
}
