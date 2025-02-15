package pedroleonez.maisbarato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedroleonez.maisbarato.domain.model.ShoppingListModel;

import java.util.UUID;

public interface ShoppingListRepository extends JpaRepository<ShoppingListModel, UUID> {
}
