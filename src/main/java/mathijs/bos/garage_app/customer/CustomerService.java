package mathijs.bos.garage_app.customer;

import mathijs.bos.garage_app.base_classes.BaseRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseService<Customer> {


    public CustomerService(BaseRepository<Customer> repository) {
        super(repository);
    }
}