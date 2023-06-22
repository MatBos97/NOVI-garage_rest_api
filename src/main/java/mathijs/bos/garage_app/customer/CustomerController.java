package mathijs.bos.garage_app.customer;

import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController<Customer> {

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        super(customerService);
    }
}
