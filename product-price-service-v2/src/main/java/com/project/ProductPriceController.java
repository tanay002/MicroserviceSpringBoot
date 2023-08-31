package com.project;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Product;
import com.project.repository.ProductRepository;

@RestController
public class ProductPriceController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ProductRepository productRepository;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());  
	
	@GetMapping("/product-price/productCode/{productCode}/isAvailable/{isAvailable}") 
	public Product retreiveProductDetails(@PathVariable String productCode,@PathVariable Boolean isAvailable)
	{
		logger.info("retrievied value with {} to {}",productCode,isAvailable);
		Product product=productRepository.findByProductCodeAndIsAvailable(productCode, isAvailable);
		
		if(product==null)
			throw new ProductNotFoundException("Product is not available in stock");
		
		product.setEnvironment(environment.getProperty("server.port"));
		
		logger.info("{}",product);
		return product;
		
	}
}
