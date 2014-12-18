package com.home.application;

import com.home.domain.Customer;
import com.home.domain.CustomerRepository;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class ApplicationServiceImplUnitTest {
    @Test
    public void testAddSuffixToEntitiesBeginningWithEmptyTable() {
        CustomerRepository repository = mock(CustomerRepository.class);
        when(repository.getEntities()).thenReturn(Collections.<Customer>emptyList());
        ApplicationServiceImpl service = new ApplicationServiceImpl();
        service.setRepository(repository);

        service.addSuffixToEntitiesBeginningWith(" VIP", 'W');

        verify(repository, times(1)).getEntities();
        verify(repository, never()).editEntity(any());
    }

    @Test
    public void testAddSuffixToEntitiesBeginningWithNoSuchEntities() {
        CustomerRepository repository = mock(CustomerRepository.class);
        Customer customer = new Customer(1, "Black", "John", "Charles", 37);
        List<Customer> entities = Arrays.asList(customer);
        when(repository.getEntities()).thenReturn(entities);
        ApplicationServiceImpl service = new ApplicationServiceImpl();
        service.setRepository(repository);

        service.addSuffixToEntitiesBeginningWith(" VIP", 'W');

        verify(repository, times(1)).getEntities();
        verify(repository, never()).editEntity(any());
    }

    @Test
    public void testAddSuffixToEntitiesBeginningWith() {
        CustomerRepository repository = mock(CustomerRepository.class);
        Customer customer = new Customer(1, "White", "Adam", "Sam", 31);
        List<Customer> entities = Arrays.asList(customer);
        when(repository.getEntities()).thenReturn(entities);
        ApplicationServiceImpl service = new ApplicationServiceImpl();
        service.setRepository(repository);

        service.addSuffixToEntitiesBeginningWith(" VIP", 'W');

        verify(repository, times(1)).getEntities();
        verify(repository, times(1)).editEntity(any());
    }

    @Test
    public void testDeleteEntitiesBeginningWithEmptyTable() {
        CustomerRepository repository = mock(CustomerRepository.class);
        when(repository.getEntities()).thenReturn(Collections.<Customer>emptyList());
        ApplicationServiceImpl service = new ApplicationServiceImpl();
        service.setRepository(repository);

        service.deleteEntitiesBeginningWith('W');

        verify(repository, times(1)).getEntities();
        verify(repository, never()).removeEntity(any());
    }

    @Test
    public void testDeleteEntitiesBeginningWithNoSuchEntities() {
        CustomerRepository repository = mock(CustomerRepository.class);
        Customer customer = new Customer(1, "Black", "John", "Charles", 37);
        List<Customer> entities = Arrays.asList(customer);
        when(repository.getEntities()).thenReturn(entities);
        ApplicationServiceImpl service = new ApplicationServiceImpl();
        service.setRepository(repository);

        service.deleteEntitiesBeginningWith('W');

        verify(repository, times(1)).getEntities();
        verify(repository, never()).removeEntity(any());
    }

    @Test
    public void testDeleteEntitiesBeginningWith() {
        CustomerRepository repository = mock(CustomerRepository.class);
        Customer customer = new Customer(1, "White", "Adam", "Sam", 31);
        List<Customer> entities = Arrays.asList(customer);
        when(repository.getEntities()).thenReturn(entities);
        ApplicationServiceImpl service = new ApplicationServiceImpl();
        service.setRepository(repository);

        service.deleteEntitiesBeginningWith('W');

        verify(repository, times(1)).getEntities();
        verify(repository, times(1)).removeEntity(any());
    }
}
