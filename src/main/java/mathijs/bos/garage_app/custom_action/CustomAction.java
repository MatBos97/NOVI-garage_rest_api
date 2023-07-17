package mathijs.bos.garage_app.custom_action;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseEntity;
import mathijs.bos.garage_app.service_record.ServiceRecord;

import java.util.Currency;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "custom_action")
public class CustomAction extends BaseEntity {

    public CustomAction(Long id, String description, Currency price) {
        this.setId(id);
        this.description = description;
        this.price = price;
    }

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Currency price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_record_id", nullable = false)
    private ServiceRecord serviceRecord;

}