package mathijs.bos.garage_app.service_record;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.action.Action;
import mathijs.bos.garage_app.base_classes.BaseEntity;
import mathijs.bos.garage_app.car.Car;
import mathijs.bos.garage_app.custom_action.CustomAction;
import mathijs.bos.garage_app.issue.Issue;
import mathijs.bos.garage_app.part.Part;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "service_record")
public class ServiceRecord extends BaseEntity {

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

}