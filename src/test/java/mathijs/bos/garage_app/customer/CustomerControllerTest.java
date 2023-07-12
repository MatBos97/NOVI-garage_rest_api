package mathijs.bos.garage_app.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    private CustomerService serviceMock;

    @InjectMocks
    private CustomerController controller;

    private Customer customer;


    @BeforeEach
    void setUp() {
        controller = new CustomerController(serviceMock);
        this.customer = new Customer(1L, "A", "123");
    }


    @Test
    public void FindAllCustomers(){
        //Arrange
        List<Customer> entities = new ArrayList<>();
        entities.add(new Customer(1L, "John", "1234"));
        entities.add(new Customer(2L, "Bart", "5678"));
        entities.add(new Customer(3L, "Jane", "9101"));
        when(serviceMock.findAll()).thenReturn(entities);

        //Act
        ResponseEntity<List<Customer>> response = controller.findAll();

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entities, response.getBody());
        verify(serviceMock, times(1)).findAll();
        verifyNoMoreInteractions(serviceMock);
    }

    @Test
    public void FindById(){
        //Arrange
        Customer customer = new Customer(1L, "A", "1234567890");
        when(serviceMock.findById(1L)).thenReturn(Optional.of(customer));

        //Act
        ResponseEntity<Customer> response = controller.findById(1L);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(serviceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(serviceMock);
    }

    @Test
    public void NotFoundById(){
        //Arrange
        when(serviceMock.findById(anyLong())).thenReturn(Optional.empty());

        //Act
        ResponseEntity<?> response = controller.findById(1L);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void CreateNewCustomer(){
        //Arrange
        Customer customer = new Customer(1L, "John", "06123456789");
        when(serviceMock.create(customer)).thenReturn(customer);

        //Act
        ResponseEntity<Customer> response = controller.create(customer);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(serviceMock, times(1)).create(customer);
        verifyNoMoreInteractions(serviceMock);
    }

    @Test
    public void UpdateCustomer() throws IllegalAccessException {
        // Arrange
        Customer newCustomer = new Customer(1L, "B", "321");
        when(serviceMock.update(anyLong(), any(Customer.class))).thenReturn(newCustomer);

        // Act
        ResponseEntity<Customer> response = controller.update(customer.getId(), customer);

        // Assert
        assertEquals(newCustomer, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void UpdateNonExistingCustomer() throws IllegalAccessException {
        //  Arrange
        Customer newCustomer = new Customer(1L, "B", "321");
        when(serviceMock.update(anyLong(), any(Customer.class))).thenThrow(IllegalAccessException.class);

        // Act
        ResponseEntity<Customer> response = controller.update(1L, newCustomer);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}