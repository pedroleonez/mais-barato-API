package pedroleonez.maisbarato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedroleonez.maisbarato.domain.model.ProductModel;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
