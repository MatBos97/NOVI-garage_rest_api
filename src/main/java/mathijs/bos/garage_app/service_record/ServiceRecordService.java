package mathijs.bos.garage_app.service_record;

import mathijs.bos.garage_app.base_classes.BaseRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRecordService extends BaseService<ServiceRecord> {

    @Autowired
    public ServiceRecordService(ServiceRecordRepository repository) {
        super(repository);
    }
}
