package pedroleonez.maisbarato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedroleonez.maisbarato.model.ShoppingListModel;

import java.util.List;
import java.util.UUID;

public interface ShoppingListRepository extends JpaRepository<ShoppingListModel, UUID> {
}
