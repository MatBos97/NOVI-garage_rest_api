package mathijs.bos.garage_app.customer;

import jakarta.persistence.EntityNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    private CustomerService serviceMock;

    @InjectMocks
    private CustomerController controller;


    @BeforeEach
    void setUp() {
        controller = new CustomerController(serviceMock);
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
        when(serviceMock.findById(1L)).thenReturn(customer);

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
        when(serviceMock.findById(1L)).thenThrow(EntityNotFoundException.class);

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
    public void UpdateCustomer(){
        // Arrange
        Customer updatedCustomer = new Customer(1L, "John", "1234");
        when(serviceMock.update(eq(1L), any(Customer.class)))
                .thenReturn(Optional.of(updatedCustomer));

        // Act
        ResponseEntity<?> response = controller.update(1L, updatedCustomer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCustomer, response.getBody());
    }

    @Test
    public void UpdateNonExistingCustomer(){
        //Arrange
        Customer updatedCustomer = new Customer(1L, "John", "1234");
        when(serviceMock.update(eq(1L), any(Customer.class)))
                .thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = controller.update(1L, updatedCustomer);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Customer not found.", response.getBody());
    }
}