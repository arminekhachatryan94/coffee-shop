package com.api.controllers;

import com.datastax.driver.core.utils.UUIDs;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.models.Product;
import com.api.services.ProductService;
 
@RestController
@RequestMapping("products")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class ProductController {
	@Autowired
	private ProductService productService;
 
	@GetMapping("/")
	public ResponseEntity<?> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		JSONObject obj = new JSONObject();
		obj.put("products", products);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> deleteProduct(@RequestBody Product product) {
		UUID generated_id = UUIDs.timeBased();
		boolean created = productService.createProduct(
			generated_id,
			product.getName(),
			product.getSize(),
			product.getPrice()
		);
		JSONObject obj = new JSONObject();
		if(created) {
			obj.put("id", generated_id);
			obj.put("message", "Successfully created product.");
			obj.put("level", "success");
			return new ResponseEntity<>(obj, HttpStatus.CREATED);
		}
		obj.put("message", "Unable to create product.");
		obj.put("level", "danger");
		return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping("/update/{id}/name")
	public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody Product product) {
		int product_exists = productService.existsProduct(id);
		JSONObject obj = new JSONObject();
		if(product_exists != 0) {
			boolean updated = productService.updateProductName(id, product.getName());
			if(updated) {
				obj.put("message", "Product successfully updated.");
				obj.put("level", "success");
				return new ResponseEntity<>(obj, HttpStatus.OK);
			}
			obj.put("message", "Unable to update.");
			obj.put("level", "danger");
			return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		obj.put("message", "Product does not exists.");
		obj.put("level", "danger");
		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}/size")
	public ResponseEntity<?> updateProductSize(@PathVariable UUID id, @RequestBody Product product) {
		int product_exists = productService.existsProduct(id);
		JSONObject obj = new JSONObject();
		if(product_exists != 0) {
			boolean updated = productService.updateProductSize(id, product.getSize());
			if(updated) {
				obj.put("message", "Product successfully updated.");
				obj.put("level", "success");
				return new ResponseEntity<>(obj, HttpStatus.OK);
			}
			obj.put("message", "Unable to update.");
			obj.put("level", "danger");
			return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		obj.put("message", "Product does not exists.");
		obj.put("level", "danger");
		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}/price")
	public ResponseEntity<?> updateProductPrice(@PathVariable UUID id, @RequestBody Product product) {
		int product_exists = productService.existsProduct(id);
		JSONObject obj = new JSONObject();
		if(product_exists != 0) {
			boolean updated = productService.updateProductPrice(id, product.getPrice());
			if(updated) {
				obj.put("message", "Product successfully updated.");
				obj.put("level", "success");
				return new ResponseEntity<>(obj, HttpStatus.OK);
			}
			obj.put("message", "Unable to update.");
			obj.put("level", "danger");
			return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		obj.put("message", "Product does not exists.");
		obj.put("level", "danger");
		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
		int product_exists = productService.existsProduct(id);
		JSONObject obj = new JSONObject();
		if(product_exists != 0) {
			boolean deleted = productService.deleteProduct(id);
			if(deleted) {
				obj.put("message", "Product successfully deleted.");
				obj.put("level", "success");
				return new ResponseEntity<>(obj, HttpStatus.OK);
			}
			obj.put("message", "Unable to update.");
			obj.put("level", "danger");
			return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		obj.put("message", "Product does not exists.");
		obj.put("level", "danger");
		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
	}
}
