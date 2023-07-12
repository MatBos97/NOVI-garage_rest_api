package mathijs.bos.garage_app.customer;

import mathijs.bos.garage_app.base_classes.BaseCon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseCon<Customer, Long, CustomerService> {

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        super(customerService);
    }
}
