package pedroleonez.maisbarato.controller;

import org.springframework.web.bind.annotation.*;
import pedroleonez.maisbarato.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}

