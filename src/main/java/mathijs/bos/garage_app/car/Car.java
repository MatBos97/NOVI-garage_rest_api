package mathijs.bos.garage_app.car;

import jakarta.persistence.*;
import lombok.*;
import mathijs.bos.garage_app.base_classes.BaseEntity;
import mathijs.bos.garage_app.car_papers.CarPapers;
import mathijs.bos.garage_app.customer.Customer;
import mathijs.bos.garage_app.service_record.ServiceRecord;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
public class Car extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceRecord> serviceRecords = new ArrayList<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarPapers> carPapers = new ArrayList<>();

}