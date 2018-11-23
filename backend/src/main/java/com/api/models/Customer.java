package com.api.models;
 
import java.util.UUID;
 
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
 
@Table(value = "customers")
public class Customer {

	@PrimaryKey
	private UUID id;
 
	private String first_name;
	private String last_name;
    private String email;
 
	public Customer() {
	}
 
	public UUID getId() {
		return id;
	}
 
	public void setId(UUID id) {
		this.id = id;
	}
 
	public String getFirstName() {
		return first_name;
	}
 
	public void setFirstName(String first_name) {
		this.first_name = first_name;
    }
    
    public String getLastName() {
		return last_name;
	}
 
	public void setLastName(String last_name) {
		this.first_name = first_name;
    }
    
    public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
    }
    
	@Override
	public String toString() {
		return "Customer [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + "]";
	}
 
}
