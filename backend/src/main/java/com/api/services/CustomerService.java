package com.api.services;
 
import java.util.List;
import java.util.UUID;
 
import org.springframework.data.cassandra.repository.CassandraRepository;
 
import com.api.models.Customer;
 
public interface CustomerService extends CassandraRepository<Customer, UUID> {
	List<Customer> findByEmail(String email);
}
