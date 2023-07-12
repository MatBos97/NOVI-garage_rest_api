package mathijs.bos.garage_app.customer;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    private Customer customer;

    @BeforeEach
    void setUp() {
        service = new CustomerService(repository);
        customer = new Customer(1L, "A", "123");
    }


    @Test
    public void FindAllCustomers(){
        //Arrange
        List<Customer> entities = new ArrayList<>();
        entities.add(new Customer(1L, "a", "123"));
        entities.add(new Customer(2L, "b", "456"));
        entities.add(new Customer(3L, "c", "789"));
        when(repository.findAll()).thenReturn(entities);

        //Act
        List<Customer> all = service.findAll();

        //Assert
        assertEquals(all, entities);
    }

    @Test
    public void FindById(){
        //Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(customer));

        //Act
        Optional<Customer> optionalCustomer = service.findById(id);

        //Assert
        assertTrue(optionalCustomer.isPresent());
        assertEquals(customer, optionalCustomer.get());
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void CannotFindById(){
        //Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(EntityNotFoundException.class, () -> service.findById(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void CreateCustomer(){
        //Arrange
        when(repository.save(customer)).thenReturn(customer);

        //Act
        Customer response = service.create(customer);

        //Assert
        assertEquals(customer, response);
    }

    @Test
    public void UpdateCustomer() throws IllegalAccessException {
        // Arrange
        Customer newCustomer = new Customer(1L, "A", "321");
        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        when(repository.save(customer)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        // Act
        Customer updatedCustomer = service.update(1L, newCustomer);

        // Assert
        assertNotNull(updatedCustomer);
        assertEquals(customer.getId(), newCustomer.getId());
        assertEquals(customer.getName(), newCustomer.getName());
        assertEquals(customer.getPhone(), newCustomer.getPhone());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(customer);
    }

    @Test
    public void DeleteCustomer(){
        //Arrange
        when(repository.findById(customer.getId())).thenReturn(Optional.of(customer));

        //Act
        service.delete(1L);

        //Assert
        verify(repository, times(1)).delete(customer);
        verify(repository, times(1)).findById(customer.getId());
    }

    @Test
    public void DeleteUnknownCustomer(){
        //Arrange
        when(repository.findById(customer.getId())).thenReturn(Optional.empty());

        //Act and Assert
        assertThrows(EntityNotFoundException.class, () -> service.delete(1L));
        verify(repository, times(1)).findById(1L);
        verify(repository, never()).delete(any());
    }
}