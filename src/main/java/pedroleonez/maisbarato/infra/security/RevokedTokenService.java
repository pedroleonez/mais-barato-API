package pedroleonez.maisbarato.infra.security;

import org.springframework.stereotype.Service;
import pedroleonez.maisbarato.repositories.RevokedTokenRepository;

@Service
public class RevokedTokenService {

    private static RevokedTokenRepository revokedTokenRepository = null;

    public RevokedTokenService(RevokedTokenRepository revokedTokenRepository) {
        RevokedTokenService.revokedTokenRepository = revokedTokenRepository;
    }

    public static void logout(String token) {
        if (!revokedTokenRepository.existsByToken(token)) {
            revokedTokenRepository.save(new RevokedTokenModel(token));
        }
    }
}

