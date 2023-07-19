package mathijs.bos.garage_app.service_record;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import mathijs.bos.garage_app.car.Car;
import mathijs.bos.garage_app.customer.Customer;
import mathijs.bos.garage_app.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceRecordService extends BaseService<ServiceRecord, ServiceRecordDTO, Long> {

    private final ServiceRecordRepository repository;
    @Autowired
    public ServiceRecordService(ServiceRecordRepository repository, ServiceRecordMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
    }

    @Override
    public ServiceRecord create(ServiceRecordDTO dto) throws EntityNotFoundException {
        return null;
    }

    @Override
    public ServiceRecord update(Long aLong, ServiceRecordDTO dto) throws EntityNotFoundException {
        return null;
    }

    public List<Customer> getCustomersWithCarsReadyForPickup(){
        List<Car> carsReadyForPickup = repository.findByStatus(Status.READY_FOR_PICK_UP).stream()
                .map(ServiceRecord::getCar)
                .toList();

        return carsReadyForPickup.stream()
                .map(Car::getCustomer)
                .toList();
    }

    public Customer getCustomerDetails(Long serviceRecordId) {
        ServiceRecord serviceRecord = repository.findById(serviceRecordId)
                .orElseThrow(() -> new ServiceRecordNotFoundException(serviceRecordId));

        return serviceRecord.getCar().getCustomer();
    }

    public List<Issue> getIssuesToFix(Long serviceRecordId){
        ServiceRecord serviceRecord = repository.findById(serviceRecordId)
                .orElseThrow(() -> new ServiceRecordNotFoundException(serviceRecordId));

        return serviceRecord.getIssues().stream()
                .filter(Issue::getFixAgreement)
                .toList();
    }

    public ServiceRecord planInspection(Long id, LocalDateTime dateTime) {
        return repository.findById(id)
                .map(serviceRecord -> {
                    serviceRecord.setInspection(dateTime);
                    return repository.save(serviceRecord);
                }).orElseThrow(() -> new ServiceRecordNotFoundException(id));
    }

    public ServiceRecord planRepair(Long id, LocalDateTime dateTime) {
        return repository.findById(id)
                .map(serviceRecord -> {
                    serviceRecord.setRepair(dateTime);
                    return repository.save(serviceRecord);
                }).orElseThrow(() -> new ServiceRecordNotFoundException(id));
    }

    public ServiceRecord changeStatus(Long id, Status status) {
        return repository.findById(id)
                .map(serviceRecord -> {
                    serviceRecord.setStatus(status);
                    return repository.save(serviceRecord);
                }).orElseThrow(() -> new ServiceRecordNotFoundException(id));
    }


}
