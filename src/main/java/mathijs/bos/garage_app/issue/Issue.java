package mathijs.bos.garage_app.issue;

import jakarta.persistence.*;
import mathijs.bos.garage_app.service_record.ServiceRecord;

@Entity
@Table(name = "issue")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "fix_agreement", nullable = false)
    private Boolean fixAgreement = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_record_id", nullable = false)
    private ServiceRecord serviceRecord;

    public ServiceRecord getServiceRecord() {
        return serviceRecord;
    }

    public void setServiceRecord(ServiceRecord serviceRecord) {
        this.serviceRecord = serviceRecord;
    }

    public Boolean getFixAgreement() {
        return fixAgreement;
    }

    public void setFixAgreement(Boolean fixAgreement) {
        this.fixAgreement = fixAgreement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}