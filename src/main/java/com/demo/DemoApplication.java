package com.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.demo.entity.Address;
import com.demo.entity.Employee;
import com.demo.repository.AddressRepository;
import com.demo.repository.EmployeeRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

//	@PersistenceContext
//    private EntityManager em;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		save();
		
		// find id in database
//		readEmp(3);
		
//		readAdd(3);
		
//		deleteParent(2);
	
	}


	private void deleteParent(int id) {
		Employee emp = employeeRepository.findById(id).orElse(null);
		if(emp!=null) {
			System.out.println("Employee found in db");
			employeeRepository.delete(emp);
		}
	}

	private void readAdd(int id) {
		Address dbAdd = addressRepository.findById(id).orElse(null);
		System.out.println(dbAdd);
		System.out.println(dbAdd.getEmployee());
// see logs for the db queries
	}

	private void readEmp(int id) {
		Employee dbEmp = employeeRepository.findById(id).orElse(null);
		System.out.println(dbEmp);
		System.out.println(dbEmp.getAddress());
		// see logs for the db queries
	}

	private void save() {
		Employee emp = new Employee();
		emp.setName("Mark");
		
		employeeRepository.save(emp);

		
		Address address = new Address();
		// use employee primary-key in address
		address.setId(emp.getId());
		address.setCity("new york");
		
		addressRepository.save(address);
	}

}
