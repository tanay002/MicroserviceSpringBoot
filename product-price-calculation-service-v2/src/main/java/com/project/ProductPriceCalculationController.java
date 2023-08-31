package com.project;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.bean.PriceCalculation;
import com.project.exception.ProductNotFoundException;
import com.project.service.ProductPriceProxy;

@RestController
public class ProductPriceCalculationController {

	@Autowired
	private ProductPriceProxy proxy;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass()); 
	
	
	@GetMapping("/productprice-calculation/productCode/{productCode}/isAvailable/{isAvailable}/quantity/{quantity}") 
	public PriceCalculation calculateOverallProductsPrice(@PathVariable String productCode,@PathVariable Boolean isAvailable,@PathVariable Integer quantity)
	{
		logger.info("retrievied value with {} to {}",productCode,isAvailable);
		Map<String,Object> uriVariables=new HashMap<String,Object>();
		 uriVariables.put("productCode", productCode);
		 uriVariables.put("isAvailable", isAvailable);
	/*	 ResponseEntity<PriceCalculation>	responseEntity= new RestTemplate()
			.getForEntity("http://localhost:8000/product-price/productCode/{productCode}/isAvailable/{isAvailable}", 
					PriceCalculation.class,uriVariables);
	*/
		 
		 ResponseEntity<PriceCalculation>	responseEntity= 
					restTemplate.getForEntity("http://localhost:8000/product-price/productCode/{productCode}/isAvailable/{isAvailable}", 
							PriceCalculation.class,uriVariables);
		 PriceCalculation response= responseEntity.getBody();
	

	logger.info("{}",response);
	
	if(response==null)
		throw new ProductNotFoundException("Product Not Available in dbase");
	
	if(response.getAvailableQuantity()<quantity)
		throw new ProductNotFoundException("Product Quantity is not available in current stock...Available Quanitty is "+response.getAvailableQuantity());
	
	PriceCalculation priceCalculation2 =  new PriceCalculation(response.getId(),
			response.getProductName(),response.getProductCode(),response.getCategory(),
			response.getPrice(),response.getIsAvailable(),response.getAvailableQuantity());
			priceCalculation2.setEnvironment(response.getEnvironment()+ " - Rest Template");
			priceCalculation2.setQuantityTobuy(quantity);
			priceCalculation2.setOverallPrice(quantity*response.getPrice());
			
			return priceCalculation2;
	}
	
	@GetMapping("/productprice-calculation-feign/productCode/{productCode}/isAvailable/{isAvailable}/quantity/{quantity}") 
	public PriceCalculation calculateOverallProductsPriceFeign(@PathVariable String productCode,@PathVariable Boolean isAvailable,@PathVariable Integer quantity)
	{
		logger.info("retrievied value with {} to {}",productCode,isAvailable);
	PriceCalculation response= proxy.retreiveProductDetails(productCode, isAvailable);
	logger.info("{}",response);
	
	if(response==null)
		throw new ProductNotFoundException("Product Not Available in dbase");
	
	if(response.getAvailableQuantity()<quantity)
		throw new ProductNotFoundException("Product Quantity is not available in current stock...Available Quanitty is "+response.getAvailableQuantity());
	
	PriceCalculation priceCalculation2 =  new PriceCalculation(response.getId(),
			response.getProductName(),response.getProductCode(),response.getCategory(),
			response.getPrice(),response.getIsAvailable(),response.getAvailableQuantity());
			priceCalculation2.setEnvironment(response.getEnvironment()+ " - Rest Template");
			priceCalculation2.setQuantityTobuy(quantity);
			priceCalculation2.setOverallPrice(quantity*response.getPrice());
			
			return priceCalculation2;
	}
}
