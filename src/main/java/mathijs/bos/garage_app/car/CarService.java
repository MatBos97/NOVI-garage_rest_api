package mathijs.bos.garage_app.car;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService extends BaseService<Car, CarDTO, Long> {

    private final CarRepository repository;
    private final CarMapper mapper;

    @Autowired
    public CarService(CarRepository repository, CarMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Car create(CarDTO dto) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Car update(Long aLong, CarDTO dto) throws EntityNotFoundException {
        return null;
    }
}
