package com.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.demo.entities.Address;
import com.demo.entities.Employee;

public class TestJpa {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence_name");
		
		when_insert_update_PARENT_no_need_auto_insert_CHILD(emf);
		
		PARENT_entity_no_need_load_CHILD_table(emf);
		
		but_when_search_CHILD_all_marched_PARENT_records_must_be_returned_at_the_same_time(emf);
		
		when_delete_PARENT_given_record_by_id_CHILD_must_be_cascade_deleted(emf);
		
		emf.close();

	}
	
	
	private void when_insert_CHILD_check_if_parent_primary_key_if_exist_if_so_inserted_else_throw_persist_warning(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		Employee emp = em.find(Employee.class, 1);
		
		if(emp == null) {
			// throw exception
		} else {
			// continue here
		}
	}
	

	private static void when_delete_PARENT_given_record_by_id_CHILD_must_be_cascade_deleted(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		
		// delete employee with id = 1 and all its addresses, while deleting addresses, related employees will get deleted as well
		Employee emp = em.find(Employee.class, 1);
		em.remove(emp);
		
		em.getTransaction().commit();
		em.close();
	}



	private static void but_when_search_CHILD_all_marched_PARENT_records_must_be_returned_at_the_same_time(
			EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		/*
		  Address foundAdd = em.find(Address.class, 1);
		  
		  em.close();
		  
		  // in Entity fetch type must be EAGER otherwise, org.hibernate.LazyInitializationException will be reaised
		  System.out.println(foundAdd); System.out.println(foundAdd.getEmployees());
		 */
		
		
		
		Query query = em.createQuery("SELECT e FROM Employee e LEFT JOIN e.addresses a ON a.city = :cityName")
			.setParameter("cityName", "Tokyo");
        List<Employee> emps = query.getResultList();
        
        System.out.println(emps);
		
		
	}

	private static void PARENT_entity_no_need_load_CHILD_table(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		Employee foundEmp = em.find(Employee.class, 1);

//		em.close();

		System.out.println(foundEmp);
		// if trying to fetch address before closing Manager, new query will be sent to db
		// if trying to fetch address after closing entityManager, org.hibernate.LazyInitializationException will be reaised
		System.out.println(foundEmp.getAddresses());
		
		em.close();
	}

	
	
	private static void when_insert_update_PARENT_no_need_auto_insert_CHILD(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Employee emp1 = new Employee();
		emp1.setName("Emp 1");
		Employee emp2 = new Employee();
		emp2.setName("Emp 2");
		
		Address add1 = new Address();
		add1.setCity("Tokyo");
		Address add2 = new Address();
		add2.setCity("NewYork");
		
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(emp1);
		emps.add(emp2);
		List<Address> adds = new ArrayList<Address>();
		adds.add(add1);
		adds.add(add2);
		
		emp1.setAddresses(adds);
		emp2.setAddresses(adds);
		
		add1.setEmployees(emps);
		add2.setEmployees(emps);
		
		
		
		em.persist(emp1);
		em.persist(emp2);
		
		// auto insert is disabled
		// to enable, change from @ManyToMany to @ManyToMany(cascade = CascadeType.ALL) in Employee entity
		em.persist(add1);
		em.persist(add2);
		
		
		// employee without address
		Employee emp3 = new Employee();
		emp3.setName("Emp 3");
		
		em.persist(emp3);
		
		em.getTransaction().commit();
		em.close();

		
	}
}
