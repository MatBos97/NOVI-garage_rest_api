package mathijs.bos.garage_app.service_record;

import jakarta.persistence.*;
import mathijs.bos.garage_app.action.Action;
import mathijs.bos.garage_app.car.Car;
import mathijs.bos.garage_app.custom_action.CustomAction;
import mathijs.bos.garage_app.issue.Issue;
import mathijs.bos.garage_app.part.Part;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;

@Entity
@Table(name = "service_record")
public class ServiceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "inspection")
    private LocalDateTime inspection;

    @Column(name = "repair")
    private LocalDateTime repair;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Lob
    @Column(name = "receipt")
    private Byte[] receipt;

    @Column(name = "total_cost")
    private Currency totalCost;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "service_record_parts",
            joinColumns = @JoinColumn(name = "service_record_id"),
            inverseJoinColumns = @JoinColumn(name = "parts_id"))
    private Collection<Part> parts = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "service_record_actions",
            joinColumns = @JoinColumn(name = "service_record_id"),
            inverseJoinColumns = @JoinColumn(name = "actions_id"))
    private Collection<Action> actions = new ArrayList<>();

    @OneToMany(mappedBy = "serviceRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomAction> customActions = new ArrayList<>();

    @OneToMany(mappedBy = "serviceRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issues = new ArrayList<>();

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<CustomAction> getCustomActions() {
        return customActions;
    }

    public void setCustomActions(List<CustomAction> customActions) {
        this.customActions = customActions;
    }

    public Collection<Action> getActions() {
        return actions;
    }

    public void setActions(Collection<Action> actions) {
        this.actions = actions;
    }

    public Collection<Part> getParts() {
        return parts;
    }

    public void setParts(Collection<Part> parts) {
        this.parts = parts;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Currency getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Currency totalCost) {
        this.totalCost = totalCost;
    }

    public Byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(Byte[] receipt) {
        this.receipt = receipt;
    }

    public LocalDateTime getRepair() {
        return repair;
    }

    public void setRepair(LocalDateTime repair) {
        this.repair = repair;
    }

    public LocalDateTime getInspection() {
        return inspection;
    }

    public void setInspection(LocalDateTime inspection) {
        this.inspection = inspection;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}