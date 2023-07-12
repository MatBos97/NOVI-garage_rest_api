package mathijs.bos.garage_app.action;

import mathijs.bos.garage_app.base_classes.BaseSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActionService extends BaseSer<Action, Long, ActionRepository> {

    @Autowired
    public ActionService(ActionRepository actionRepository) {
        super(actionRepository);
    }

}
