package mathijs.bos.garage_app.part;

import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/part")
public class PartController extends BaseController<Part, PartDTO, Long> {

    @Autowired
    public PartController(PartService service) {
        super(service);
    }
}
