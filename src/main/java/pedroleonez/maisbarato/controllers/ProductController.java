package pedroleonez.maisbarato.controllers;

import org.springframework.web.bind.annotation.*;
import pedroleonez.maisbarato.dtos.product.AddProductDTO;
import pedroleonez.maisbarato.dtos.product.ProductDTO;
import pedroleonez.maisbarato.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProduct(@RequestBody AddProductDTO dto) {
        productService.addProduct(dto);
    }

    @GetMapping
    public List<ProductDTO> listProducts() {
        return productService.listProducts();
    }

    @PutMapping("{id}")
    public void updateProduct(@PathVariable("id") String id, @RequestBody AddProductDTO dto) {
        productService.updateProduct(UUID.fromString(id), dto);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(UUID.fromString(id));
    }
}

