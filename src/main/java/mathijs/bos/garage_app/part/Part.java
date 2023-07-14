package mathijs.bos.garage_app.part;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseEntity;

import java.util.Currency;

@Getter
@Setter
@Entity
@Table(name = "part")
public class Part extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false, length = 50)
    private Currency price;

    @Column(name = "stock", nullable = false)
    private Integer stock;
}