package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService extends BaseService<Car> {

    @Autowired
    public CarService(CarRepository repository) {
        super(repository);
    }
}
