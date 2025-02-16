package pedroleonez.maisbarato.domain.model;

import jakarta.persistence.*;
import lombok.*;
import pedroleonez.maisbarato.domain.model.enums.Unit;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private double price1;

    private double size1;

    private double price2;

    private double size2;

    @Enumerated(EnumType.STRING)
    private Unit unit;
}
