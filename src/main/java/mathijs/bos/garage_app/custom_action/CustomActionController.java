package mathijs.bos.garage_app.custom_action;

import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom_action")
public class CustomActionController extends BaseController<CustomAction, Long, CustomActionService> {

    @Autowired
    public CustomActionController(CustomActionService service) {
        super(service);
    }
}
