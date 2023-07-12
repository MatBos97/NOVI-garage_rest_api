package mathijs.bos.garage_app.action;

import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/action")
public class ActionController extends BaseController<Action, Long, ActionService> {

    @Autowired
    public ActionController(ActionService actionService) {
        super(actionService);
    }
}
