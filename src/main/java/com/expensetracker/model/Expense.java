package com.expensetracker.model;

import jakarta.persistence.*;
import java.util.*;
import java.time.*;
import java.math.*;
@Entity

public class Expense {
	@Id
	@GeneratedValue
	Long id;
	private String description;
	private BigDecimal amount;
	private LocalDate date;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
public Expense() {
	
}
public Expense(Long id,String description,BigDecimal amount,LocalDate date,User user) {
	this.id=id;
	this.description=description;
	this.amount=amount;
	this.date=date;
	this.user=user;
	
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public BigDecimal getAmount() {
	return amount;
}
public void setAmount(BigDecimal amount) {
	this.amount = amount;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}


}
