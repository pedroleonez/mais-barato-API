package pedroleonez.maisbarato.infra.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RevokedTokenModel {

    @Id
    private String token;

    private LocalDateTime revokedAt;

    public RevokedTokenModel(String token) {
        this.token = token;
        this.revokedAt = LocalDateTime.now();
    }
}

