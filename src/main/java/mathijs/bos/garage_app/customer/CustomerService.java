package mathijs.bos.garage_app.customer;

import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService extends BaseService<Customer, Long, CustomerRepository> {

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        super(customerRepository);
    }
}
