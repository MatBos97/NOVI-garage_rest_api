package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseController;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController extends BaseController<Car> {

    @Autowired
    public CarController(CarService service) {
        super(service);
    }
}
