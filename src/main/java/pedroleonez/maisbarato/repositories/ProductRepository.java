package pedroleonez.maisbarato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pedroleonez.maisbarato.domain.models.ProductModel;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
