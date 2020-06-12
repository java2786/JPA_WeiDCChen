package com.demo;

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

		Employee emp = new Employee();
		emp.setName("Mark");
		
		employeeRepository.save(emp);

		
		Address address = new Address();
		// use employee primary in address
		address.setId(emp.getId());
		address.setCity("new york");
		

//		employeeRepository.save(emp);
		
		
		
		addressRepository.save(address);
		
		
	
	}

}
