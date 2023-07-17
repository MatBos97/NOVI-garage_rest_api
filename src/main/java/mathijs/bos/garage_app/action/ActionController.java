package mathijs.bos.garage_app.action;

import mathijs.bos.garage_app.base_classes.BaseController;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/action")
public class ActionController extends BaseController<Action, ActionDTO, Long> {

    @Autowired
    public ActionController(BaseService<Action, ActionDTO, Long> service) {
        super(service);

    }
}
