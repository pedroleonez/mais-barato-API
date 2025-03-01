package pedroleonez.maisbarato.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pedroleonez.maisbarato.domain.models.ProductModel;
import pedroleonez.maisbarato.domain.models.enums.Unit;
import pedroleonez.maisbarato.dtos.product.AddProductDTO;
import pedroleonez.maisbarato.dtos.product.ProductDTO;
import pedroleonez.maisbarato.exception.InvalidProductDataException;
import pedroleonez.maisbarato.exception.ProductNotFoundException;
import pedroleonez.maisbarato.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductModel product;
    private AddProductDTO addProductDTO;

    @BeforeEach
    void setUp() {
        product = new ProductModel();
        product.setId(UUID.randomUUID());
        product.setName("Test Product");
        product.setPrice1(BigDecimal.valueOf(10.0));
        product.setSize1(BigDecimal.valueOf(1.0));
        product.setPrice2(BigDecimal.valueOf(18.0));
        product.setSize2(BigDecimal.valueOf(2.0));
        product.setUnit(Unit.KG);

        addProductDTO = new AddProductDTO("Test Product", BigDecimal.valueOf(10.0), BigDecimal.valueOf(1.0),
                BigDecimal.valueOf(18.0), BigDecimal.valueOf(2.0), Unit.KG);
    }

    @Test
    void addProduct_ShouldSaveProduct() {
        productService.addProduct(addProductDTO);
        verify(productRepository, times(1)).save(any(ProductModel.class));
    }

    @Test
    void addProduct_ShouldThrowException_WhenNameIsNull() {
        AddProductDTO invalidProduct = new AddProductDTO(null, new BigDecimal("10.0"), new BigDecimal("1.0"), new BigDecimal("18.0"), new BigDecimal("2.0"), pedroleonez.maisbarato.domain.models.enums.Unit.KG);
        assertThrows(InvalidProductDataException.class, () -> productService.addProduct(invalidProduct));
    }

    @Test
    void listProducts_ShouldReturnListOfProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductDTO> products = productService.listProducts();

        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertEquals("Test Product", products.getFirst().name());
    }

    @Test
    void listProducts_ShouldThrowExceptionWhenEmpty() {
        when(productRepository.findAll()).thenReturn(List.of());

        assertThrows(ProductNotFoundException.class, () -> productService.listProducts());
    }

    @Test
    void updateProduct_ShouldUpdateProduct() {
        UUID id = product.getId();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        productService.updateProduct(id, addProductDTO);

        verify(productRepository, times(1)).save(any(ProductModel.class));
    }

    @Test
    void updateProduct_ShouldThrowExceptionIfNotFound() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(id, addProductDTO));
    }

    @Test
    void deleteProduct_ShouldDeleteProduct() {
        UUID id = UUID.randomUUID();
        when(productRepository.existsById(id)).thenReturn(true);

        productService.deleteProduct(id);

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteProduct_WhenIdNotFound_ShouldThrowException() {
        UUID id = UUID.randomUUID();
        when(productRepository.existsById(id)).thenReturn(false);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(id));

        assertEquals("Product not found with id: " + id, exception.getMessage());
        verify(productRepository, never()).deleteById(any(UUID.class)); // Garante que não tentou deletar
    }

    @Test
    void getBestOption_ShouldReturnBestOption() {
        UUID id = product.getId();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        String result = productService.getBestOption(id);

        assertTrue(result.contains("The second option"));
    }

    @Test
    void getBestOption_ShouldThrowExceptionIfProductNotFound() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getBestOption(id));
    }

    @Test
    void getBestOption_ShouldThrowExceptionIfDataIsMissing() {
        product.setPrice1(null);
        UUID id = product.getId();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        assertThrows(InvalidProductDataException.class, () -> productService.getBestOption(id));
    }
}