package com.project.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.bean.PriceCalculation;

//@FeignClient(name="product-price",url = "localhost:8000")
@FeignClient(name="product-price")
public interface ProductPriceProxy {

	@GetMapping("/product-price/productCode/{productCode}/isAvailable/{isAvailable}") 
	public PriceCalculation retreiveProductDetails(@PathVariable String productCode,@PathVariable Boolean isAvailable);

}
