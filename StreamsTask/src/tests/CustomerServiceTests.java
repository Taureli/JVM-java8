package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.Before;
import services.CustomerService;
import services.CustomerServiceInterface;
import entities.Customer;

public class CustomerServiceTests {
	
	private CustomerServiceInterface cs;
	
	@Before
	public void preTests(){
		cs = new CustomerService(DataProducer.getTestData(10));
	}

	@Test
	public void testFindByName() {		
		List<Customer> res = cs.findByName("Customer: 1");
		
		assertNotNull("Result can't be null", res);
		assertEquals(1, res.size());
		
	}
	
	@Test
	public void testFindByField() {		
		List<Customer> res = cs.findByField("id", "2");
		
		assertNotNull("Result can't be null", res);
		assertEquals(1, res.size());
	}

	@Test
	public void testCustomersWhoBoughtMoreThan() {		
		List<Customer> res = cs.customersWhoBoughtMoreThan(3);
		
		assertNotNull("Result can't be null", res);
		assertEquals(3, res.size());
	}
	
	
}
