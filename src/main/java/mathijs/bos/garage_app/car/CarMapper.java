package mathijs.bos.garage_app.car;

import mathijs.bos.garage_app.base_classes.BaseMapper;
import mathijs.bos.garage_app.car_papers.CarPapers;
import mathijs.bos.garage_app.service_record.ServiceRecord;

import java.util.List;

public class CarMapper implements BaseMapper<Car, CarDTO> {


    @Override
    public Car toEntity(CarDTO dto) {
        return null;
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

    @Override
    public List<Car> toEntity(List<CarDTO> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public List<CarDTO> toDto(List<Car> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .toList();
    }
}
