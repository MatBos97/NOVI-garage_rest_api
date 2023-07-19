package mathijs.bos.garage_app.customer;

import mathijs.bos.garage_app.base_classes.BaseMapper;
import mathijs.bos.garage_app.car.Car;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements BaseMapper<Customer, CustomerDTO> {
    @Override
    public Customer toEntity(CustomerDTO dto) {
        return new Customer(
                dto.getId(),
                dto.getName(),
                dto.getPhone()
        );
    }

    @Override
    public CustomerDTO toDto(Customer entity) {
        return new CustomerDTO(
                entity.getName(),
                entity.getPhone(),
                entity.getCars().stream().map(Car::getId).toList()
        );
    }
}
