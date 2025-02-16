package pedroleonez.maisbarato.domain.models;

import jakarta.persistence.*;
import lombok.*;
import pedroleonez.maisbarato.domain.models.enums.Unit;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
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

    @Column
    private BigDecimal price1;

    @Column
    private BigDecimal size1;

    @Column
    private BigDecimal price2;

    @Column
    private BigDecimal size2;

    @Enumerated(EnumType.STRING)
    @Column
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
