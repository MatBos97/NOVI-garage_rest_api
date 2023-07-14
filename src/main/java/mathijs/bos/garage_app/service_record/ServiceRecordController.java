package mathijs.bos.garage_app.service_record;

import mathijs.bos.garage_app.base_classes.BaseController;
import mathijs.bos.garage_app.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/service_record")
public class ServiceRecordController extends BaseController<ServiceRecord, Long, ServiceRecordService> {

    private final ServiceRecordService service;

    @Autowired
    public ServiceRecordController(ServiceRecordService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/ready_for_pickup")
    public ResponseEntity<List<Customer>> getCustomersWithCarsReadyForPickup(){
        List<Customer> readyForPickup = service.getCustomersWithCarsReadyForPickup();
        return ResponseEntity.ok(readyForPickup);
    }

    @PatchMapping("/plan_inspection/{id}")
    public ResponseEntity<ServiceRecord> planInspection(@PathVariable Long id, @RequestBody LocalDateTime dateTime){
        try {
            ServiceRecord serviceRecord = service.planInspection(id, dateTime);
            return ResponseEntity.ok(serviceRecord);
        } catch (ServiceRecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/plan_repair/{id}")
    public ResponseEntity<ServiceRecord> planRepair(@PathVariable Long id, @RequestBody LocalDateTime dateTime) {
        try {
            ServiceRecord serviceRecord = service.planRepair(id, dateTime);
            return ResponseEntity.ok(serviceRecord);
        } catch (ServiceRecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
