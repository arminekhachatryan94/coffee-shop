package com.api.controllers;

import com.datastax.driver.core.utils.UUIDs;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}/name")
	public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody Product product) {
		int product_exists = productService.existsProduct(id);
		if(product_exists != 0) {
			boolean updated = productService.updateProductName(id, product.getName());
			if(updated) {
				return new ResponseEntity<String>("Product successfully updated.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Unable to update.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Product does not exists.", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}/size")
	public ResponseEntity<?> updateProductSize(@PathVariable UUID id, @RequestBody Product product) {
		int product_exists = productService.existsProduct(id);
		if(product_exists != 0) {
			boolean updated = productService.updateProductSize(id, product.getSize());
			if(updated) {
				return new ResponseEntity<String>("Product successfully updated.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Unable to update.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Product does not exists.", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}/price")
	public ResponseEntity<?> updateProductPrice(@PathVariable UUID id, @RequestBody Product product) {
		int product_exists = productService.existsProduct(id);
		if(product_exists != 0) {
			boolean updated = productService.updateProductPrice(id, product.getPrice());
			if(updated) {
				return new ResponseEntity<String>("Product successfully updated.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Unable to update.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Product does not exists.", HttpStatus.NOT_FOUND);
	}
}
