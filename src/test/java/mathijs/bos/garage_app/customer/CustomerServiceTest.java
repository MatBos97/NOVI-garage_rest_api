package mathijs.bos.garage_app.customer;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.car.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CustomerService service;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        service = new CustomerService(customerRepository, customerMapper, carRepository);
        customer = new Customer(1L, "A", "123456789");
        customerDTO = new CustomerDTO("A", "123456789", null);
    }


    @Test
    public void FindAllCustomers(){
        //Arrange
        List<Customer> customerList = List.of(
                new Customer(1L, "A", "123456789"),
                new Customer(1L, "A", "123456789")
        );
        List<CustomerDTO> customerDTOList = List.of(
                new CustomerDTO("A", "123456789", null),
                new CustomerDTO("A", "123456789", null)
        );
        when(customerRepository.findAll()).thenReturn(customerList);
        when(customerMapper.toDto(customerList)).thenReturn(customerDTOList);

        //Act
        List<CustomerDTO> actual = service.findAll();

        //Assert
        assertEquals(customerDTOList, actual);
        assertEquals(customerDTOList.size(), actual.size());
    }

    @Test
    public void FindById(){
        //Arrange
        Long id = 1L;
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        //Act
        CustomerDTO foundCustomer = service.findById(id);

        //Assert
        assertEquals(customerDTO, foundCustomer);
        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    public void CannotFindById(){
        //Arrange
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        //Act & Assert
        assertThrows(EntityNotFoundException.class, ()-> service.findById(1L));
    }

    @Test
    public void CreateCustomer(){
        //Arrange
        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(carRepository.findById(anyLong())).thenReturn(null);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDTO);

        //Act
        CustomerDTO response = service.create(customerDTO);

        //Assert
        assertEquals(customerDTO, response);
    }

    @Test
    public void UpdateCustomer() throws IllegalAccessException {
        // Arrange
        CustomerDTO newCustomer = new CustomerDTO("A", "321", null);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        CustomerDTO updatedCustomer = service.update(1L, newCustomer);

        // Assert
        assertNotNull(updatedCustomer);
        assertEquals(customer.getId(), newCustomer.getId());
        assertEquals(customer.getName(), newCustomer.getName());
        assertEquals(customer.getPhone(), newCustomer.getPhone());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void DeleteCustomer(){
        //Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        //Act
        service.delete(1L);

        //Assert
        verify(customerRepository, times(1)).delete(customer);
        verify(customerRepository, times(1)).findById(customer.getId());
    }

    @Test
    public void DeleteUnknownCustomer(){
        //Arrange
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Act and Assert
        assertThrows(EntityNotFoundException.class, () -> service.delete(1L));
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, never()).delete(any());
    }
}