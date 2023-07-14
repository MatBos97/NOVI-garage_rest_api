package mathijs.bos.garage_app.car_papers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseEntity;
import mathijs.bos.garage_app.car.Car;

@Getter
@Setter
@Entity
@Table(name = "car_papers")
public class CarPapers extends BaseEntity {

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Lob
    @Column(name = "file_data", nullable = false)
    private Byte[] fileData;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

}