package com.demo.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String city;
	
	// when delete CHILD, no need to delete PARENT
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private Collection<Employee> employees;

	@Override
	public String toString() {
		// Using employee in toString will result StackOverFlow error--> employee has address and adress has employees
		return "{" + " id='" + id + "'" + ", city='" + city + "'" + "}";
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Collection<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}

}
