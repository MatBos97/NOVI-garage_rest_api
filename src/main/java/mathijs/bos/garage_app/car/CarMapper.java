package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseMapper;
import mathijs.bos.garage_app.car_papers.CarPapers;
import mathijs.bos.garage_app.service_record.ServiceRecord;

public class CarMapper implements BaseMapper<Car, CarDTO> {


    @Override
    public Car toEntity(CarDTO dto) {
        return new Car(
                dto.getId()
        );
    }

    @Override
    public CarDTO toDto(Car entity) {
        return new CarDTO(
                entity.getId(),
                entity.getCustomer().getId(),
                entity.getServiceRecords().stream()
                        .map(ServiceRecord::getId)
                        .toList(),
                entity.getCarPapers().stream()
                        .map(CarPapers::getId)
                        .toList()
        );
    }
}
