package mathijs.bos.garage_app.car;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import mathijs.bos.garage_app.car_papers.CarPapers;
import mathijs.bos.garage_app.car_papers.CarPapersRepository;
import mathijs.bos.garage_app.customer.Customer;
import mathijs.bos.garage_app.customer.CustomerRepository;
import mathijs.bos.garage_app.service_record.ServiceRecord;
import mathijs.bos.garage_app.service_record.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService extends BaseService<Car, CarDTO, Long> {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ServiceRecordRepository serviceRecordRepository;
    private final CarMapper carMapper;
    private final CarPapersRepository carPapersRepository;

    @Autowired
    public CarService(
            CarRepository carRepository,
            CarMapper carMapper,
            CustomerRepository customerRepository,
            ServiceRecordRepository serviceRecordRepository,
            CarPapersRepository carPapersRepository){
        super(carRepository, carMapper);
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.serviceRecordRepository = serviceRecordRepository;
        this.carMapper = carMapper;
        this.carPapersRepository = carPapersRepository;
    }

    @Override
    public CarDTO create(CarDTO dto) throws EntityNotFoundException {
        dto.setId(null);
        Car car = carMapper.toEntity(dto);

        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(EntityNotFoundException::new);

        List<ServiceRecord> serviceRecords = dto.getServiceRecordIdList().stream()
                .map(
                        serviceRecordId -> serviceRecordRepository.findById(serviceRecordId).orElseThrow(EntityNotFoundException::new)
                ).toList();

        List<CarPapers> carPapers = dto.getCarPapersIdList().stream()
                .map(
                        carPapersId -> carPapersRepository.findById(carPapersId).orElseThrow(EntityNotFoundException::new)
                ).toList();

        car.setCustomer(customer);
        car.setServiceRecords(serviceRecords);
        car.setCarPapers(carPapers);

        return carMapper.toDto(car);
    }

    @Override
    public CarDTO update(Long id, CarDTO dto) throws EntityNotFoundException {
        Car updated = carRepository.findById(id).map(
                car -> {
                    car.setId(dto.getId());
                    car.setCustomer(customerRepository.findById(dto.getCustomerId()).orElseThrow(EntityNotFoundException::new));

                    car.setServiceRecords(dto.getServiceRecordIdList().stream().map(
                            serviceRecordId -> serviceRecordRepository.findById(serviceRecordId).orElseThrow(EntityNotFoundException::new)
                    ).toList());

                    car.setCarPapers(dto.getCarPapersIdList().stream().map(
                            carPapersId -> carPapersRepository.findById(carPapersId).orElseThrow(EntityNotFoundException::new)
                    ).toList());

                    return carRepository.save(car);
                }).orElseThrow(EntityNotFoundException::new);
        return carMapper.toDto(updated);
    }
}
