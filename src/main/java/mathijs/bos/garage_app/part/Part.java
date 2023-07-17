package mathijs.bos.garage_app.part;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseEntity;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "part")
public class Part extends BaseEntity {

    public Part(Long id, String name, Currency price, Integer stock) {
        this.setId(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false, length = 50)
    private Currency price;

    @Column(name = "stock", nullable = false)
    private Integer stock;
}