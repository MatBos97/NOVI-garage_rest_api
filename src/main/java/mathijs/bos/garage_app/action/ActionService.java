package mathijs.bos.garage_app.action;

import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActionService extends BaseService<Action, Long, ActionRepository> {

    @Autowired
    public ActionService(ActionRepository actionRepository) {
        super(actionRepository);
    }

}
