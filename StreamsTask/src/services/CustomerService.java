package services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.Customer;
import entities.Product;

public class CustomerService implements CustomerServiceInterface {

	private List<Customer> customers;

	public CustomerService(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public List<Customer> findByName(String name) {
		List<Customer> result = new ArrayList<Customer>();
		
		customers.stream().map(s -> s.getName()).map(s -> s.compareToIgnoreCase(name));
		
		return result;
	}

	@Override
	public List<Customer> findByField(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> customersWhoBoughtMoreThan(int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> customersWhoSpentMoreThan(double price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> customersWithNoOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProductToAllCustomers(Product p) {
		// TODO Auto-generated method stub

	}

	@Override
	public double avgOrders(boolean includeEmpty) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean wasProductBought(Product p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Product> mostPopularProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countBuys(Product p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countCustomersWhoBought(Product p) {
		// TODO Auto-generated method stub
		return 0;
	}

}