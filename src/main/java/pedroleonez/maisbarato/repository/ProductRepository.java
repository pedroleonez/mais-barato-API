package pedroleonez.maisbarato.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import pedroleonez.maisbarato.model.ProductModel;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
