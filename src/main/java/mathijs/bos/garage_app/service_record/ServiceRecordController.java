package mathijs.bos.garage_app.service_record;

import mathijs.bos.garage_app.base_classes.BaseController;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service_record")
public class ServiceRecordController extends BaseController<ServiceRecord> {

    @Autowired
    public ServiceRecordController(ServiceRecordService service) {
        super(service);
    }
}
