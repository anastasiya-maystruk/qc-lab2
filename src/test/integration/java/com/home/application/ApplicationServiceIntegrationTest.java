package com.home.application;

import com.home.domain.Customer;
import com.home.domain.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationServiceIntegrationTest {
    @Autowired
    JdbcTemplate template;
    @Autowired
    ApplicationService service;
    @Autowired
    CustomerRepository repository;

    @Before
    public void setUp() throws IOException {
        executeScript("scripts/create_table.sql");
    }

    @Test
    public void testAddSuffixToEntitiesBeginningWithEmptyTable() {
        service.addSuffixToEntitiesBeginningWith(" VIP", 'W');

        assertEquals(Collections.<Customer>emptyList(), repository.getEntities());
    }

    @Test
    public void testAddSuffixToEntitiesBeginningWithNoSuchEntities() throws IOException {
        executeScript("scripts/no_such_entities.sql");

        service.addSuffixToEntitiesBeginningWith(" VIP", 'W');

        Customer[] expected = new Customer[]{new Customer(1, "Black", "Jack", "Sam", 28)};
        assertEquals(Arrays.asList(expected), repository.getEntities());
    }

    @Test
    public void testAddSuffixToEntitiesBeginningWith() throws IOException {
        executeScript("scripts/insert_needed_entities.sql");

        service.addSuffixToEntitiesBeginningWith(" VIP", 'W');

        Customer[] expected = new Customer[]{
                new Customer(1, "White VIP", "Adam", "John", 30),
                new Customer(2, "Black", "Jack", "Sam", 28),
                new Customer(3, "Winter VIP", "Patric", "Nick", 31)
        };
        assertEquals(Arrays.asList(expected), repository.getEntities());
    }

    @Test
    public void testDeleteEntitiesBeginningWithEmptyTable() {
        service.deleteEntitiesBeginningWith('W');

        assertEquals(Collections.<Customer>emptyList(), repository.getEntities());
    }

    @Test
    public void testDeleteEntitiesBeginningWithNoSuchEntities() throws IOException {
        executeScript("scripts/no_such_entities.sql");

        service.deleteEntitiesBeginningWith('W');

        Customer[] expected = new Customer[]{new Customer(1, "Black", "Jack", "Sam", 28)};
        assertEquals(Arrays.asList(expected), repository.getEntities());
    }

    @Test
    public void testDeleteEntitiesBeginningWith() throws IOException {
        executeScript("scripts/insert_needed_entities.sql");

        service.deleteEntitiesBeginningWith('W');

        Customer[] expected = new Customer[]{new Customer(2, "Black", "Jack", "Sam", 28)};
        assertEquals(Arrays.asList(expected), repository.getEntities());
    }

    private void executeScript(String fileName) throws IOException {
        Resource script = new ClassPathResource(fileName);

        LineNumberReader scriptReader = new LineNumberReader(new BufferedReader(new FileReader(script.getFile())));

        String commentPrefix = "--";
        String separator = ";";
        String queries = ScriptUtils.readScript(scriptReader, commentPrefix, separator);

        StringTokenizer queryTokenizer = new StringTokenizer(queries, ";");
        while (queryTokenizer.hasMoreTokens()) {
            String query = queryTokenizer.nextToken();
            template.execute(query);
        }
    }
}
