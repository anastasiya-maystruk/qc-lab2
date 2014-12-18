package com.home.domain;

import com.home.domain.Customer;
import com.home.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    private JdbcTemplate template;

    @Override
    public Customer getEntity(long id) {
        List<Customer> results = template.query("SELECT * FROM customers WHERE id = ?",
                new Object[]{id},
                (resultSet, i) -> new Customer(id,
                        resultSet.getString("surname"),
                        resultSet.getString("name"),
                        resultSet.getString("patronymic"),
                        resultSet.getInt("age")
                )
        );
        return results.get(0);
    }

    @Override
    public void editEntity(Customer editedEntity) {
        template.update("UPDATE customers SET surname = ?, name = ?, patronymic = ?, age = ? WHERE id = ?",
                editedEntity.getSurname(),
                editedEntity.getName(),
                editedEntity.getPatronymic(),
                editedEntity.getAge(),
                editedEntity.getId()
        );
    }

    @Override
    public void removeEntity(Customer deletedEntity) {
        template.update("DELETE FROM customers WHERE id = ?", deletedEntity.getId());
    }

    @Override
    public List<Customer> getEntities() {
        return template.query("SELECT * FROM customers",
                (resultSet, i) -> new Customer(resultSet.getLong("id"),
                        resultSet.getString("surname"),
                        resultSet.getString("name"),
                        resultSet.getString("patronymic"),
                        resultSet.getInt("age")
                )
        );
    }
}
