package com.api.services;
 
import java.util.List;
import java.util.UUID;
 
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import com.api.models.Customer;

public interface CustomerService extends CassandraRepository<Customer, UUID> {
    @Query("SELECT * FROM coffeeshop.customers")
    List<Customer> getAllCustomers();

    @Query("SELECT * FROM coffeeshop.customers WHERE id = :id")
    Customer getCustomer(UUID id);

    // @Query("SELECT * FROM coffeeshop.customers WHERE first_name = ? PER PARTITION LIMIT 1 ALLOW FILTERING;")
    // Customer getCustomerByFirstName(String first_name);
}
