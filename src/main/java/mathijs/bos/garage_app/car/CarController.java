package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController extends BaseController<Car, Long, CarService> {

    @Autowired
    public CarController(CarService carService) {
        super(carService);
    }

}
