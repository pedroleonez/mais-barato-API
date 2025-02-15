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

    // Criar uma nova lista de compras
    @Transactional
    public ShoppingListModel createShoppingList(UUID userId, @NotNull ShoppingListModel shoppingList) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        shoppingList.setUser(user); // Associa a lista ao usuário
        return shoppingListRepository.save(shoppingList);
    }

    // Buscar todas as listas de compras de um usuário
    public List<ShoppingListModel> getUserShoppingLists(UUID userId) {
        return shoppingListRepository.findByUserId(userId);
    }

    // Buscar uma lista de compras por ID
    public Optional<ShoppingListModel> getShoppingListById(UUID listId) {
        return shoppingListRepository.findById(listId);
    }

    // Atualizar o nome da lista de compras
    @Transactional
    public ShoppingListModel updateShoppingList(UUID listId, String newName) {
        ShoppingListModel shoppingList = shoppingListRepository.findById(listId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping list not found"));

        shoppingList.setName(newName);
        return shoppingListRepository.save(shoppingList);
    }

    // Excluir uma lista de compras
    @Transactional
    public void deleteShoppingList(UUID listId) {
        if (!shoppingListRepository.existsById(listId)) {
            throw new IllegalArgumentException("Shopping list not found");
        }
        shoppingListRepository.deleteById(listId);
    }

    // Buscar todos os produtos dentro de uma lista de compras
    public Set<ProductModel> getProductsInShoppingList(UUID shoppingListId) {
        ShoppingListModel shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping List not found"));
        return shoppingList.getProducts();
    }

    // Adicionar um produto à lista de compras
    @Transactional
    public ShoppingListModel addProductToShoppingList(UUID shoppingListId, UUID productId) {
        ShoppingListModel shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping List not found"));

        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        shoppingList.getProducts().add(product);
        return shoppingListRepository.save(shoppingList);
    }

    // Remover um produto da lista de compras
    @Transactional
    public ShoppingListModel removeProductFromShoppingList(UUID shoppingListId, UUID productId) {
        ShoppingListModel shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping List not found"));

        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        shoppingList.getProducts().remove(product);
        return shoppingListRepository.save(shoppingList);
    }
}