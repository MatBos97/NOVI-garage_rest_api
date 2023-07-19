package mathijs.bos.garage_app.service_record;

import mathijs.bos.garage_app.base_classes.BaseController;
import mathijs.bos.garage_app.customer.Customer;
import mathijs.bos.garage_app.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/service_record")
public class ServiceRecordController extends BaseController<ServiceRecord, ServiceRecordDTO, Long> {

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

    @GetMapping("/{id}/customer_details")
    public ResponseEntity<Customer> getCustomerDetails(@PathVariable Long id){
        Customer customer = service.getCustomerDetails(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}/issues_to_fix")
    public ResponseEntity<List<Issue>> getIssuesToFix(@PathVariable Long id) {
        List<Issue> issuesToFix = service.getIssuesToFix(id);
        return ResponseEntity.ok(issuesToFix);
    }

    @PatchMapping("/{id}/plan_inspection")
    public ResponseEntity<ServiceRecord> planInspection(@PathVariable Long id, @RequestBody LocalDateTime dateTime){
        try {
            ServiceRecord serviceRecord = service.planInspection(id, dateTime);
            return ResponseEntity.ok(serviceRecord);
        } catch (ServiceRecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/plan_repair")
    public ResponseEntity<ServiceRecord> planRepair(@PathVariable Long id, @RequestBody LocalDateTime dateTime) {
        try {
            ServiceRecord serviceRecord = service.planRepair(id, dateTime);
            return ResponseEntity.ok(serviceRecord);
        } catch (ServiceRecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/change_status")
    public ResponseEntity<ServiceRecord> changeStatus(@PathVariable Long id, @RequestBody Status status){
        try {
            ServiceRecord serviceRecord = service.changeStatus(id, status);
            return ResponseEntity.ok(serviceRecord);
        } catch (ServiceRecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
