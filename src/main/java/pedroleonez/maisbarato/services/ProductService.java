package pedroleonez.maisbarato.services;

import org.springframework.stereotype.Service;
import pedroleonez.maisbarato.domain.models.ProductModel;
import pedroleonez.maisbarato.domain.models.enums.Unit;
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

    // compare product sizes and prices
    public String getBestOption(UUID productId) {
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // verifica se os preços e tamanhos foram informados
        if (product.getPrice1() == null || product.getSize1() == null ||
                product.getPrice2() == null || product.getSize2() == null) {
            return "Not enough information to compare.";
        }

        // calculates the price per unit
        double unitPrice1 = product.getPrice1().doubleValue() / product.getSize1().doubleValue();
        double unitPrice2 = product.getPrice2().doubleValue() / product.getSize2().doubleValue();

        // determines the best option and the percentage savings
        String unit = formatUnit(product.getUnit());

        if (unitPrice1 < unitPrice2) {
            double savings = ((unitPrice2 - unitPrice1) / unitPrice2) * 100;
            return String.format("The first option (%.2f %s) is %.2f%% more economical.",
                    product.getSize1(), unit, savings);
        } else if (unitPrice1 > unitPrice2) {
            double savings = ((unitPrice1 - unitPrice2) / unitPrice1) * 100;
            return String.format("The second option (%.2f %s) is %.2f%% more economical.",
                    product.getSize2(), unit, savings);
        } else {
            return "Both options have the same cost per unit.";
        }
    }

    // format unit
    private String formatUnit(Unit unit) {
        return switch (unit) {
            case KG -> "kilograms";
            case GRAM -> "grams";
            case LITER -> "liters";
            case MILLILITER -> "milliliters";
            case UNIT -> "units";
        };
    }
}
