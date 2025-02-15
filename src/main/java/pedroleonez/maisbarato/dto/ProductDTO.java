package pedroleonez.maisbarato.dto;

import lombok.Getter;
import lombok.Setter;
import pedroleonez.maisbarato.domain.model.enums.Unit;

import java.util.UUID;

@Getter
@Setter
public class ProductDTO {
    private UUID id;
    private String name;
    private double price1;
    private double size1;
    private double price2;
    private double size2;
    private Unit unit;
}
