package pedroleonez.maisbarato.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_SHOPPING_LISTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @ManyToMany
    @JoinTable(name = "shopping_list_product",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<ProductModel> products = new LinkedHashSet<>();
}
