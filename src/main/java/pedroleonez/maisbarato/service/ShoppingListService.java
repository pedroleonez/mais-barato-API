package pedroleonez.maisbarato.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pedroleonez.maisbarato.model.ProductModel;
import pedroleonez.maisbarato.model.ShoppingListModel;
import pedroleonez.maisbarato.model.UserModel;
import pedroleonez.maisbarato.repository.ProductRepository;
import pedroleonez.maisbarato.repository.ShoppingListRepository;
import pedroleonez.maisbarato.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
}