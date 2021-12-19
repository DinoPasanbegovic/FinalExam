package ca.sheridancollege.pasanbed.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class Food implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private String type;
	private BigDecimal price;
	private int id;
	private BigDecimal quantity;
	
	
	
	public Food(String name, BigDecimal price, int id, String type, BigDecimal quantity) {
		super();
		this.name = name;
		this.price = price;
		this.id = id;
		this.type = type;
		this.quantity = quantity;
	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Food() {
		super();
	}
	
	public Food(String name, BigDecimal price, String type) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Food [Name=" + name + ", price=" + price + ", id=" + id + ", type=" + type + ", quantity=" + quantity +"]";
	}
	public BigDecimal getPrice() {
		return price ;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}