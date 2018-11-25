package com.api.services;
 
import java.util.List;
import java.util.UUID;
 
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.api.models.Product;

@CrossOrigin(origins="http://localhost:4200")
public interface ProductService extends CassandraRepository<Product, UUID> {
    @Query("SELECT * FROM coffeeshop.products")
    List<Product> getAllProducts();

    @Query("SELECT count(*) FROM coffeeshop.products WHERE product_id = :id")
    int existsProduct(UUID id);

    @Query("INSERT INTO coffeeshop.products (product_id, name, size, price) VALUES (now(), :name, :size, :price) IF NOT EXISTS")
    boolean createProduct(String name, int size, double price);

    @Query("UPDATE coffeeshop.products SET name = :name WHERE product_id = :id IF EXISTS")
    boolean updateProductName(UUID id, String name);

    @Query("UPDATE coffeeshop.products SET size = :size WHERE product_id = :id IF EXISTS")
    boolean updateProductSize(UUID id, int size);

    @Query("UPDATE coffeeshop.products SET price = :price WHERE product_id = :id IF EXISTS")
    boolean updateProductPrice(UUID id, double price);
}
