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

    @Query("INSERT INTO coffeeshop.users (id, first_name, last_name, email, password, role) values (now(), :first_name, :last_name, :email, :password, :role) IF NOT EXISTS")
    boolean createUser(String first_name, String last_name, String email, String password, String role);

    @Query("UPDATE coffeeshop.users SET first_name = :first_name, last_name = :last_name, email = :email WHERE id = :id IF EXISTS")
    boolean updateUser(UUID id, String first_name, String last_name, String email);

    @Query("UPDATE coffeeshop.users (role) SET (:role) WHERE id = :id")
    boolean updateRole(UUID id, String role);
    
    @Query("UPDATE coffeeshop.users (password) SET (:password) WHERE id = :id")
    boolean updatePassword(UUID id, String password);

    @Query("DELETE FROM coffeeshop.users WHERE id = :id")
    boolean deleteUser(UUID id);
}
