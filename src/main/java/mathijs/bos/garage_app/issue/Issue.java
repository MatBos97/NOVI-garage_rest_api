package mathijs.bos.garage_app.issue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseEntity;
import mathijs.bos.garage_app.service_record.ServiceRecord;

@Getter
@Setter
@Entity
@Table(name = "issue")
public class Issue extends BaseEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "fix_agreement", nullable = false)
    private Boolean fixAgreement = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_record_id", nullable = false)
    private ServiceRecord serviceRecord;

}