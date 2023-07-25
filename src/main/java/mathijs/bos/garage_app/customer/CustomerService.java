package mathijs.bos.garage_app.customer;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import mathijs.bos.garage_app.car.Car;
import mathijs.bos.garage_app.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService extends BaseService<Customer, CustomerDTO, Long> {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CarRepository carRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, CarRepository carRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.carRepository = carRepository;
    }

    @Override
    public Customer create(CustomerDTO dto) throws EntityNotFoundException {
        dto.setId(null);
        Customer customer = customerMapper.toEntity(dto);

        List<Car> cars = dto.getCarIdList().stream().map(
                carId -> carRepository.findById(carId).orElseThrow(EntityNotFoundException::new)
        ).toList();

        customer.setCars(cars);

        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, CustomerDTO dto) throws EntityNotFoundException {

        List<Car> cars = dto.getCarIdList().stream().map(
                carId -> carRepository.findById(carId).orElseThrow(EntityNotFoundException::new)
        ).toList();

        return customerRepository.findById(id).map(
                customer -> {
                    customer.setId(dto.getId());
                    customer.setPhone(dto.getPhone());
                    customer.setName(dto.getName());
                    customer.setCars(cars);

                    return customerRepository.save(customer);
                }
        ).orElseThrow(EntityNotFoundException::new);
    }
}
