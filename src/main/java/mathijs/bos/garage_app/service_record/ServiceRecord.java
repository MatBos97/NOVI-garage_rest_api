package mathijs.bos.garage_app.service_record;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Currency;

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

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Lob
    @Column(name = "receipt")
    private Byte[] receipt;

    @Column(name = "total_cost")
    private Currency totalCost;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}