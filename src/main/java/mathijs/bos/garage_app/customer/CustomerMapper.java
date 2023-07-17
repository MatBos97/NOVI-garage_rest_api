package mathijs.bos.garage_app.customer;

import mathijs.bos.garage_app.base_classes.BaseMapper;

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
                entity.getId(),
                entity.getName(),
                entity.getPhone()
        );
    }
}
