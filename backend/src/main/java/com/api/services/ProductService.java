package com.api.services;
 
import java.util.List;
import java.util.UUID;
 
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import com.api.models.Product;

public interface ProductService extends CassandraRepository<Product, UUID> {
    @Query("SELECT * FROM coffeeshop.products")
    List<Product> getAllProducts();
}
