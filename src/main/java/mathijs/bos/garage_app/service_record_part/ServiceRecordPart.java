package mathijs.bos.garage_app.service_record_part;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseEntity;
import mathijs.bos.garage_app.part.Part;
import mathijs.bos.garage_app.service_record.ServiceRecord;

@Getter
@Setter
@Entity
public class ServiceRecordPart extends BaseEntity {

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "service_record_id", nullable = false)
    private ServiceRecord serviceRecord;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;

}
