package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.Before;

import services.CustomerService;
import services.CustomerServiceInterface;
import entities.Customer;
import entities.Product;

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
		List<Customer> res = cs.findByField("id", 2);
		
		assertNotNull("Result can't be null", res);
		assertEquals(1, res.size());
	}

	@Test
	public void testCustomersWhoBoughtMoreThan() {		
		List<Customer> res = cs.customersWhoBoughtMoreThan(4);
		
		assertNotNull("Result can't be null", res);
		assertEquals(3, res.size());
	}
	
	@Test
	public void testCustomersWhoSpentMoreThan(){
		List<Customer> res = cs.customersWhoSpentMoreThan(2);
		
		assertNotNull("Result can't be null", res);
		assertEquals(4, res.size());
	}
	
	@Test
	public void testCustomersWithNoOrders(){
		List<Customer> res = cs.customersWithNoOrders();
		
		assertNotNull("Result can't be null", res);
		assertEquals(3, res.size());
	}
	
	@Test
	public void testAddProductToAllCustomers(){
		List<Customer> list = DataProducer.getTestData(10);
		Product p = new Product(11, "Test product", 1);
		
		int amount = list.stream().mapToInt(c -> c.getBoughtProducts().size()).sum();
		cs.addProductToAllCustomers(p);
		int amountWithNew = cs.allCustomers().stream().mapToInt(c -> c.getBoughtProducts().size()).sum();
		
		assertNotEquals(amount, amountWithNew);
	}
	
	@Test
	public void testAvgOrdersWithEmpty(){
		List<Customer> list = DataProducer.getTestData(10);
		
		double average = list.stream().mapToDouble(c -> c.getBoughtProducts().stream().mapToDouble(Product::getPrice).sum()).sum();
		double result = cs.avgOrders(true);
		
		assertEquals(average/list.size(), result, 0.01);
	}
	
	@Test
	public void testAvgOrdersWithoutEmpty(){
		List<Customer> list = DataProducer.getTestData(10);
		
		double average = list.stream().filter(c -> c.getBoughtProducts().size() != 0).mapToDouble(c -> c.getBoughtProducts().stream().mapToDouble(Product::getPrice).sum()).sum() / list.stream().filter(c -> c.getBoughtProducts().size() > 0).collect(Collectors.toList()).size();
		double result = cs.avgOrders(false);
		
		assertEquals(average, result, 0.01);
	}
	
	@Test
	public void testWasProductBought(){
		Product p1 = new Product(10, "Product: 10", 1);
		Product p2 = new Product(11, "Test product", 1);
		
		assertEquals(true, cs.wasProductBought(p1));
		assertEquals(false, cs.wasProductBought(p2));
	}
	
	@Test
	public void testCountBuys(){
		Product p = new Product(9, "Product: 9", 0.9);
		
		assertEquals(2, cs.countBuys(p));
	}
	
	@Test
	public void testCountCustomersWhoBought(){
		Product p = new Product(9, "Product: 9", 0.9);
		
		assertEquals(2, cs.countCustomersWhoBought(p));
	}
	
}
