package mathijs.bos.garage_app.car_papers;

import jakarta.persistence.*;
import mathijs.bos.garage_app.car.Car;

@Entity
@Table(name = "car_papers")
public class CarPapers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_papers_seq")
    @SequenceGenerator(name = "car_papers_seq")
    @Column(name = "id", nullable = false)
    private Long id;

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Byte[] getFileData() {
        return fileData;
    }

    public void setFileData(Byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}