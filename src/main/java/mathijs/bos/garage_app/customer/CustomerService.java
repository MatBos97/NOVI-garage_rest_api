package mathijs.bos.garage_app.customer;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService extends BaseService<Customer, CustomerDTO, Long> {

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper mapper) {
        super(customerRepository, mapper);
    }

    @Override
    public Customer create(CustomerDTO dto) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Customer update(Long aLong, CustomerDTO dto) throws EntityNotFoundException {
        return null;
    }
}
