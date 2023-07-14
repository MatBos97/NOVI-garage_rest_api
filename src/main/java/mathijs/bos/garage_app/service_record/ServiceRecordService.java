package mathijs.bos.garage_app.service_record;

import mathijs.bos.garage_app.base_classes.BaseService;
import mathijs.bos.garage_app.car.Car;
import mathijs.bos.garage_app.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceRecordService extends BaseService<ServiceRecord, Long, ServiceRecordRepository> {

    private final ServiceRecordRepository repository;
    @Autowired
    public ServiceRecordService(ServiceRecordRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ServiceRecord planInspection(Long id, LocalDateTime dateTime) throws ServiceRecordNotFoundException {
        return repository.findById(id)
                .map(serviceRecord -> {
                    serviceRecord.setInspection(dateTime);
                    return repository.save(serviceRecord);
                }).orElseThrow(() -> new ServiceRecordNotFoundException(id));
    }

    public ServiceRecord planRepair(Long id, LocalDateTime dateTime) throws ServiceRecordNotFoundException {
        return repository.findById(id)
                .map(serviceRecord -> {
                    serviceRecord.setRepair(dateTime);
                    return repository.save(serviceRecord);
                }).orElseThrow(() -> new ServiceRecordNotFoundException(id));
    }

    public List<Customer> getCustomersWithCarsReadyForPickup(){
        List<Car> carsReadyForPickup = repository.findByStatus(Status.READY_FOR_PICK_UP).stream()
                .map(ServiceRecord::getCar)
                .toList();

        return carsReadyForPickup.stream()
                .map(Car::getCustomer)
                .toList();
    }

}
