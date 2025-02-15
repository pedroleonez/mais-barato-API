package pedroleonez.maisbarato.controller;

import org.springframework.web.bind.annotation.*;
import pedroleonez.maisbarato.service.ShoppingListService;

@RestController
@RequestMapping("/shopping-lists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }
}

