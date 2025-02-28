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
import pedroleonez.maisbarato.exception.InvalidProductDataException;
import pedroleonez.maisbarato.repositories.ProductRepository;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private AddProductDTO validProductDTO;

    @BeforeEach
    void setUp() {
        validProductDTO = new AddProductDTO(
                "Milk",
                new BigDecimal("10.50"),
                new BigDecimal("1.0"),
                new BigDecimal("20.00"),
                new BigDecimal("2.0"),
                Unit.LITER
        );
    }

    @Test
    void addProduct_ShouldSaveProduct_WhenValidDTO() {
        // Arrange
        when(productRepository.save(any(ProductModel.class))).thenReturn(new ProductModel());

        // Act
        productService.addProduct(validProductDTO);

        // Assert
        verify(productRepository, times(1)).save(any(ProductModel.class));
    }

    @Test
    void addProduct_ShouldThrowException_WhenNameIsNull() {
        // Arrange
        AddProductDTO invalidProductDTO = new AddProductDTO(
                null, // Nome ausente
                new BigDecimal("10.50"),
                new BigDecimal("1.0"),
                new BigDecimal("20.00"),
                new BigDecimal("2.0"),
                Unit.LITER
        );

        // Act & Assert
        assertThrows(InvalidProductDataException.class, () -> productService.addProduct(invalidProductDTO));
        verify(productRepository, never()).save(any(ProductModel.class));
    }
}