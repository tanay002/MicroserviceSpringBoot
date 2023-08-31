package com.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String productName;
	private String productCode;
    private String category;
	private Long price;
	private Boolean isAvailable;
	private Integer availableQuantity;
	
	
	@Transient
	private String environment;


	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productCode=" + productCode + ", category="
				+ category + ", price=" + price + ", isAvailable=" + isAvailable + ", availableQuantity="
				+ availableQuantity + ", environment=" + environment + "]";
	}


	public Product(Integer id, String productName, String productCode, String category, Long price, Boolean isAvailable,
			Integer availableQuantity) {
		super();
		this.id = id;
		this.productName = productName;
		this.productCode = productCode;
		this.category = category;
		this.price = price;
		this.isAvailable = isAvailable;
		this.availableQuantity = availableQuantity;
	}


	public Integer getAvailableQuantity() {
		return availableQuantity;
	}


	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Long getPrice() {
		return price;
	}


	public void setPrice(Long price) {
		this.price = price;
	}


	public Boolean getIsAvailable() {
		return isAvailable;
	}


	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	public String getEnvironment() {
		return environment;
	}


	public void setEnvironment(String environment) {
		this.environment = environment;
	}


	public Product() {
		super();
	}
	
	
}
