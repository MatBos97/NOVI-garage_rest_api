package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CarService extends BaseService<Car> {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        super(carRepository);
        this.carRepository = carRepository;
    }

    public void saveCarPapers(Long carId, MultipartFile file) throws Exception {
        Car car = findById(carId);
        if (car == null){
            throw new Exception("Car not found.");
        }

        byte[] carPapers = file.getBytes();
        car.setCarPapers(carPapers);
        carRepository.save(car);
    }
}
