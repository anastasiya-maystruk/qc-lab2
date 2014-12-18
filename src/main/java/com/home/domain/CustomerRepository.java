package com.home.domain;

import com.home.domain.Customer;

import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public interface CustomerRepository {
    Customer getEntity(long id);

    void editEntity(Customer editedEntity);

    void removeEntity(Customer deletedEntity);

    List<Customer> getEntities();
}
