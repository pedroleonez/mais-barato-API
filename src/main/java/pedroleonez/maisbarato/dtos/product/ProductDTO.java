package pedroleonez.maisbarato.dtos.product;

import pedroleonez.maisbarato.domain.models.enums.Unit;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        BigDecimal price1,
        BigDecimal size1,
        BigDecimal price2,
        BigDecimal size2,
        Unit unit
) {
}
