package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Address {

	@Id
	private int id;
	private String city;

	
	// remove below block for uni-directional mapping
	//----------- bi-directional mapping start here ------------- //
	@OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Employee employee;

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	//----------- bi-directional mapping end here ------------- //
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", employee=" + employee + "]";
	}

	

//	@Override
//	public String toString() {
//		return "Address [id=" + id + ", city=" + city + "]";
//	}

}
