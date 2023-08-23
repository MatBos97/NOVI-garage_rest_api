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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    private CustomerService serviceMock;

    @InjectMocks
    private CustomerController controller;

    private CustomerDTO customer;


    @BeforeEach
    void setUp() {
        controller = new CustomerController(serviceMock);
        this.customer = new CustomerDTO("A", "123", null);
        this.customer.setId(1L);
    }


    @Test
    public void FindAllCustomers(){
        //Arrange
        List<CustomerDTO> entities = new ArrayList<>();
        entities.add(new CustomerDTO("Alex", "1234", null));
        entities.add(new CustomerDTO("Jo", "5678", null));
        entities.add(new CustomerDTO("Jane", "9101", null));
        when(serviceMock.findAll()).thenReturn(entities);

        //Act
        ResponseEntity<List<CustomerDTO>> response = controller.findAll();

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
        CustomerDTO findById = new CustomerDTO("A", "1234567890", null);
        when(serviceMock.findById(anyLong())).thenReturn(findById);

        //Act
        ResponseEntity<CustomerDTO> response = controller.findById(1L);

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
        when(serviceMock.findById(anyLong())).thenReturn(null);

        //Act
        ResponseEntity<?> response = controller.findById(1L);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void CreateNewCustomer(){
        //Arrange
        CustomerDTO customer = new CustomerDTO("Peter Pan", "123456789", null);
        when(serviceMock.create(customer)).thenReturn(customer);

        //Act
        ResponseEntity<CustomerDTO> response = controller.create(customer);

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
        CustomerDTO newCustomer = new CustomerDTO("A", "06123456789", null);
        when(serviceMock.update(anyLong(), any(CustomerDTO.class))).thenReturn(newCustomer);

        // Act
        ResponseEntity<CustomerDTO> response = controller.update(customer.getId(), customer);

        // Assert
        assertEquals(newCustomer, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void UpdateNonExistingCustomer() throws IllegalAccessException {
        //  Arrange
        CustomerDTO newCustomer = new CustomerDTO("B", "321", null);
        when(serviceMock.update(anyLong(), any(CustomerDTO.class))).thenThrow(IllegalAccessException.class);

        // Act
        ResponseEntity<CustomerDTO> response = controller.update(1L, newCustomer);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}