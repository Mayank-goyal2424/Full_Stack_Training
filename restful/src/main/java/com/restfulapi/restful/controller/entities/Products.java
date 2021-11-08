package com.restfulapi.restful.controller.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="products")
public class Products {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productId;
	
	@NotNull
	@Size(min=4, max=30,message="Product Name should have atleast 4 characters ")
	private String productName;
	
	@Min(1)
	private int principalAmount;
	
	@Min(6)
	@Max(60)
	//@Size(min=1,message="Product Tenure should be greater than or equals to 6 months and less than or equals to 60 months(5 years)")
	private int tenure;
	
	@Min(1)
	private double rateOfInterest;
	boolean isActive;
	
	@NotNull
	String createdBy;
	
	//@NotNull
	LocalDateTime createdOn;
	@Nullable
	String updatedBy;
	
	@Nullable
	LocalDateTime updatedOn;
	public Products(int productId, String productName, int principalAmount, int tenure, double rateOfInterest,
			boolean isActive, String createdBy, String createdOn, String updatedBy, String updatedOn) {
		super();
		LocalDateTime date=LocalDateTime.now();
		this.productId = productId;
		this.productName = productName;
		this.principalAmount = principalAmount;
		this.tenure = tenure;
		this.rateOfInterest = rateOfInterest;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdOn = date;
		this.updatedBy = updatedBy;
		this.updatedOn = date;
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName.toLowerCase();
	}
	public int getPrincipalAmount() {
		return principalAmount;
	}
	public void setPrincipalAmount(int principalAmount) {
		this.principalAmount = principalAmount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy.toLowerCase();
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn() {
		LocalDateTime date=LocalDateTime.now();
		this.createdOn = date;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy.toLowerCase();
	}
	public LocalDateTime getUpdatedOn() {
		
		return updatedOn;
	}
	public void setUpdatedOn() {
		LocalDateTime date=LocalDateTime.now();
		this.updatedOn = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + principalAmount;
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rateOfInterest);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + tenure;
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((updatedOn == null) ? 0 : updatedOn.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (isActive != other.isActive)
			return false;
		if (principalAmount != other.principalAmount)
			return false;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (Double.doubleToLongBits(rateOfInterest) != Double.doubleToLongBits(other.rateOfInterest))
			return false;
		if (tenure != other.tenure)
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedOn == null) {
			if (other.updatedOn != null)
				return false;
		} else if (!updatedOn.equals(other.updatedOn))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", principalAmount="
				+ principalAmount + ", tenure=" + tenure + ", rateOfInterest=" + rateOfInterest + ", isActive="
				+ isActive + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy
				+ ", updatedOn=" + updatedOn + "]";
	}
	
	

}
