package com.api.models;
 
import java.util.UUID;
 
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
 
@Table(value = "products")
public class Product {

	@PrimaryKey
	private UUID product_id;
 
    private String name;
    private int size;
    private double price;
 
	public Product() {
	}
 
	public UUID getProductId() {
		return product_id;
	}
 
	public void setProductId(UUID product_id) {
		this.product_id = product_id;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	@Override
	public String toString() {
		return "Product [product_id=" + product_id.toString() + ", name=" + name + ", size=" + size + ", price=" + price + "]";
	}
 
}
