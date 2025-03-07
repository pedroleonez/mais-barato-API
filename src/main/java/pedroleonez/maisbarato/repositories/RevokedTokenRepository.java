package pedroleonez.maisbarato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pedroleonez.maisbarato.infra.security.RevokedTokenModel;

@Repository
public interface RevokedTokenRepository extends JpaRepository<RevokedTokenModel, String> {
    boolean existsByToken(String token);
}

