package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseEntity;
import mathijs.bos.garage_app.base_classes.BaseMapper;
import mathijs.bos.garage_app.car_papers.CarPapersRepository;
import mathijs.bos.garage_app.customer.CustomerRepository;
import mathijs.bos.garage_app.service_record.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper implements BaseMapper<CarDTO, Car> {

    private CustomerRepository customerRepository;
    private ServiceRecordRepository serviceRecordRepository;
    private CarPapersRepository carPapersRepository;

    @Autowired
    public CarMapper(CustomerRepository customerRepository, ServiceRecordRepository serviceRecordRepository, CarPapersRepository carPapersRepository) {
        this.customerRepository = customerRepository;
        this.serviceRecordRepository = serviceRecordRepository;
        this.carPapersRepository = carPapersRepository;
    }

    @Override
    public Car toEntity(CarDTO dto) {
        if(dto == null){
            return null;
        }

        Car car = Car.builder()
                .customer(customerRepository.findById(dto.getCustomerId()).orElse(null))
                .serviceRecords(dto.getServiceRecordIds().stream()
                        .map(id -> serviceRecordRepository.findById(id).orElse(null))
                        .collect(Collectors.toList()))
                .carPapers(dto.getCarPapersIds().stream()
                        .map(id -> carPapersRepository.findById(id).orElse(null))
                        .collect(Collectors.toList()))
                .build();
        car.setId(dto.getId());

        return car;
    }

    @Override
    public CarDTO toDto(Car entity) {
        if(entity == null){
            return null;
        }

        CarDTO dto = CarDTO.builder()
                .customerId(entity.getCustomer().getId())
                .serviceRecordIds(entity.getServiceRecords().stream()
                        .map(BaseEntity::getId)
                        .collect(Collectors.toList()))
                .carPapersIds(entity.getCarPapers().stream()
                        .map(BaseEntity::getId)
                        .collect(Collectors.toList()))
                .build();
        dto.setId(entity.getId());

        return dto;
    }

    @Override
    public List<Car> toEntity(List<CarDTO> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> toDto(List<Car> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
