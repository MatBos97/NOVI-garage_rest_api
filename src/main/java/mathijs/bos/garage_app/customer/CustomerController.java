package mathijs.bos.garage_app.customer;

import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController<Customer> {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        super(customerService);
        this.customerService = customerService;
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer newCustomer){
        Optional<Customer> customer = customerService.update(id, newCustomer);

        if(customer.isEmpty()){
            return new ResponseEntity<>("Customer not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }
}
