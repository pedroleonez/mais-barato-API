package pedroleonez.maisbarato.services;

import org.springframework.stereotype.Service;
import pedroleonez.maisbarato.domain.models.ProductModel;
import pedroleonez.maisbarato.dtos.product.AddProductDTO;
import pedroleonez.maisbarato.dtos.product.ProductDTO;
import pedroleonez.maisbarato.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // utility method to map DTO to ProductModel
    private ProductModel mapDtoToProductModel(ProductModel productModel, AddProductDTO dto) {
        productModel.setName(dto.name());
        productModel.setPrice1(dto.price1());
        productModel.setSize1(dto.size1());
        productModel.setPrice2(dto.price2());
        productModel.setSize2(dto.size2());
        productModel.setUnit(dto.unit());
        return productModel;
    }

    // addProduct
    public void addProduct(AddProductDTO dto) {
        ProductModel productModel = mapDtoToProductModel(new ProductModel(), dto);
        productRepository.save(productModel);
    }

    // listProducts
    public List<ProductDTO> listProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice1(),
                        product.getSize1(),
                        product.getPrice2(),
                        product.getSize2(),
                        product.getUnit()
                ))
                .toList();
    }

    // updateProduct
    public void updateProduct(UUID id, AddProductDTO dto) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        mapDtoToProductModel(productModel, dto);
        productRepository.save(productModel);
    }

    // deleteProduct
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
