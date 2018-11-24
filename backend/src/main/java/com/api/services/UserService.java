package com.api.services;
 
import java.util.List;
import java.util.UUID;
 
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import com.api.models.User;

public interface UserService extends CassandraRepository<User, UUID> {
    @Query("SELECT * FROM coffeeshop.users")
    List<User> getAllUsers();

    @Query("SELECT * FROM coffeeshop.users WHERE id = :id")
    User getUser(UUID id);
}
