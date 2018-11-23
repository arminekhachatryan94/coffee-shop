package com.api.services;
 
import java.util.List;
import java.util.UUID;
 
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import com.api.models.Customer;

public interface CustomerService extends CassandraRepository<Customer, UUID> {
    @Query("SELECT * FROM coffeeshop.customers ALLOW FILTERING")
    List<Customer> getAllCustomers();

}
