package mathijs.bos.garage_app.custom_action;

import mathijs.bos.garage_app.base_classes.BaseRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomActionService extends BaseService<CustomAction> {

    @Autowired
    public CustomActionService(CustomActionRepository repository) {
        super(repository);
    }
}
