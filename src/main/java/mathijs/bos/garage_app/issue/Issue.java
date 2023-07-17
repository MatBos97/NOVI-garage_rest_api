package mathijs.bos.garage_app.issue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseEntity;
import mathijs.bos.garage_app.service_record.ServiceRecord;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "issue")
public class Issue extends BaseEntity {

    public Issue(Long id, String description, Boolean fixAgreement) {
        this.setId(id);
        this.description = description;
        this.fixAgreement = fixAgreement;
    }

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "fix_agreement", nullable = false)
    private Boolean fixAgreement = false;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "service_record_id", nullable = false)
    private ServiceRecord serviceRecord;

}