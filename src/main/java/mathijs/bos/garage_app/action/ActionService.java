package mathijs.bos.garage_app.action;

import mathijs.bos.garage_app.base_classes.BaseRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ActionService extends BaseService<Action> {
    public ActionService(ActionRepository repository) {
        super(repository);
    }
}
