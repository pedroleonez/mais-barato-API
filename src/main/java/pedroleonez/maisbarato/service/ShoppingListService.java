package pedroleonez.maisbarato.service;

import org.springframework.stereotype.Service;
import pedroleonez.maisbarato.repository.ProductRepository;
import pedroleonez.maisbarato.repository.ShoppingListRepository;
import pedroleonez.maisbarato.repository.UserRepository;

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