package pedroleonez.maisbarato.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import java.util.Set;

@Getter
@Setter
public class ShoppingListDTO {
    private UUID id;
    private String name;
    private UUID userId; // Para saber a quem pertence a lista
    private Set<ProductDTO> products;
}

