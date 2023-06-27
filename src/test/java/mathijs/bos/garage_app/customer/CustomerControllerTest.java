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
    public void CreateNewCustomer(){
        //Arrange
        List<Customer> entities = new ArrayList<>();
        entities.add(new Customer(1L, "Mathijs", "1234"));
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
}