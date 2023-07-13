package mathijs.bos.garage_app.service_record;

import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/service_record")
public class ServiceRecordController extends BaseController<ServiceRecord, Long, ServiceRecordService> {

    private final ServiceRecordService service;

    @Autowired
    public ServiceRecordController(ServiceRecordService service) {
        super(service);
        this.service = service;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceRecord> planInspection(@PathVariable Long id, @RequestBody LocalDateTime dateTime){
        try {
            ServiceRecord serviceRecord = service.planInspection(id, dateTime);
            return ResponseEntity.ok(serviceRecord);
        } catch (ServiceRecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceRecord> planRepair(@PathVariable Long id, @RequestBody LocalDateTime dateTime) {
        try {
            ServiceRecord serviceRecord = service.planRepair(id, dateTime);
            return ResponseEntity.ok(serviceRecord);
        } catch (ServiceRecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
