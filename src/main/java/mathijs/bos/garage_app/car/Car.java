package mathijs.bos.garage_app.car;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "car_papers")
    private Byte[] carPapers;

    public Byte[] getCarPapers() {
        return carPapers;
    }

    public void setCarPapers(Byte[] carPapers) {
        this.carPapers = carPapers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}