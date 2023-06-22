package mathijs.bos.garage_app.issue;

import jakarta.persistence.*;

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