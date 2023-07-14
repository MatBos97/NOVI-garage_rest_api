package mathijs.bos.garage_app.action;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseEntity;

import java.util.Currency;

@Getter
@Setter
@Entity
@Table(name = "action")
public class Action extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false, length = 10)
    private Currency price;

}