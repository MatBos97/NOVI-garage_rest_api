package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class CarService extends BaseService<Car, Long, CarRepository> {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        super(carRepository);
        this.carRepository = carRepository;
    }

    public void saveCarPapers(Long carId, MultipartFile file) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isEmpty()){
            throw new Exception("Car not found.");
        }

        Car car = optionalCar.get();

        byte[] carPapers = file.getBytes();
        car.setCarPapers(carPapers);
        carRepository.save(car);
    }
}
