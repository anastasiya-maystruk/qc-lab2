package com.home.application;

import com.home.domain.Customer;
import com.home.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private CustomerRepository repository;

    @Transactional
    @Override
    public void addSuffixToEntitiesBeginningWith(String suffix, char ch) {
        List<Customer> entities = repository.getEntities();
        for (Customer entity : entities) {
            String surname = entity.getSurname();
            if (surname.charAt(0) == ch) {
                entity.setSurname(surname + suffix);
                repository.editEntity(entity);
            }
        }
    }

    @Transactional
    @Override
    public void deleteEntitiesBeginningWith(char ch) {
        List<Customer> entities = repository.getEntities();
        entities
                .stream()
                .filter(entity -> entity.getSurname().charAt(0) == ch)
                .forEach(repository::removeEntity);
    }

    public void setRepository(CustomerRepository repository) {
        this.repository = repository;
    }
}
