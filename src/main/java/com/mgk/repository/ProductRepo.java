package com.mgk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mgk.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	
	
	@Query("SELECT p FROM Product p WHERE "+
	        "p.name LIKE CONCAT('%', :query, '%')" +
			"or p.description LIKE CONCAT('%', :query, '%')")
	public List<Product> searchProduct(String query);

}
