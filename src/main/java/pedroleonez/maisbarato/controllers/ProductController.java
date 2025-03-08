package pedroleonez.maisbarato.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedroleonez.maisbarato.dtos.product.AddProductDTO;
import pedroleonez.maisbarato.dtos.product.ProductDTO;
import pedroleonez.maisbarato.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody AddProductDTO dto) {
        productService.addProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<ProductDTO> listProducts() {
        return productService.listProducts();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") String id, @RequestBody AddProductDTO dto) {
        productService.updateProduct(UUID.fromString(id), dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/best-option")
    public ResponseEntity<String> getBestOption(@PathVariable UUID id) {
        String bestOption = productService.getBestOption(id);
        return ResponseEntity.ok(bestOption);
    }
}

