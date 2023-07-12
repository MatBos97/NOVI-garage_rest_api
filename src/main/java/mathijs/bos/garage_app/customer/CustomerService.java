package mathijs.bos.garage_app.customer;

import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService extends BaseService<Customer> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> update(Long id, Customer newCustomer){
        return customerRepository.findById(id)
                .map(customer -> {
                            customer.setId(id);
                            customer.setName(newCustomer.getName());
                            customer.setPhone(newCustomer.getPhone());
                            customer.setCars(newCustomer.getCars());
                            customerRepository.save(customer);
                            return customer;

                });
    }
}
