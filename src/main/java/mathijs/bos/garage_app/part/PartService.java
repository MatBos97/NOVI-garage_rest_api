package mathijs.bos.garage_app.part;

import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartService extends BaseService<Part, Long, PartRepository> {

    @Autowired
    public PartService(PartRepository repository) {
        super(repository);
    }
}
