package mathijs.bos.garage_app.services;

import mathijs.bos.garage_app.car.Car;
import mathijs.bos.garage_app.car.CarRepository;
import mathijs.bos.garage_app.customer.Customer;
import mathijs.bos.garage_app.customer.CustomerRepository;
import mathijs.bos.garage_app.service_record.ServiceRecord;
import mathijs.bos.garage_app.service_record.ServiceRecordRepository;
import mathijs.bos.garage_app.service_record.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarPickUpService {

    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final ServiceRecordRepository serviceRecordRepository;

    @Autowired
    public CarPickUpService(CustomerRepository customerRepository, CarRepository carRepository, ServiceRecordRepository serviceRecordRepository){
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.serviceRecordRepository = serviceRecordRepository;
    }


}
