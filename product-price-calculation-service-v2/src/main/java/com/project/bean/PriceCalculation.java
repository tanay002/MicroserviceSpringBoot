package com.project.bean;

public class PriceCalculation {

	private Integer id;
	private String productName;
	private String productCode;
    private String category;
	private Long price;
	private Boolean isAvailable;
	private Integer availableQuantity;
	private Integer quantityTobuy;
	private Long overallPrice;
	
	
	private String environment;
 

	public Integer getQuantityTobuy() {
		return quantityTobuy;
	}


	public void setQuantityTobuy(Integer quantityTobuy) {
		this.quantityTobuy = quantityTobuy;
	}


	public Long getOverallPrice() {
		return overallPrice;
	}


	public void setOverallPrice(Long overallPrice) {
		this.overallPrice = overallPrice;
	}


	@Override
	public String toString() {
		return "PriceCalculation [id=" + id + ", productName=" + productName + ", productCode=" + productCode
				+ ", category=" + category + ", price=" + price + ", isAvailable=" + isAvailable
				+ ", availableQuantity=" + availableQuantity + ", quantityTobuy=" + quantityTobuy + ", overallPrice="
				+ overallPrice + ", environment=" + environment + "]";
	}


	public PriceCalculation(Integer id, String productName, String productCode, String category, Long price, Boolean isAvailable,
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


	public PriceCalculation() {
		super();
	}
	
	
}
