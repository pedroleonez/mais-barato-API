package pedroleonez.maisbarato.service;

import org.springframework.stereotype.Service;
import pedroleonez.maisbarato.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
