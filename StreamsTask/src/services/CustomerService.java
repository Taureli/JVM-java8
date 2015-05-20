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
	
	public List<Customer> allCustomers(){
		return customers;
	}

	@Override
	public List<Customer> findByName(String name) {
		List<Customer> result = new ArrayList<Customer>();
		
		result = customers.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
		
		return result;
	}

	@Override
	public List<Customer> findByField(String fieldName, Object value) {
		return customers.stream().filter(c -> {
			try {
				return c.getClass().getDeclaredField(fieldName).equals(value);
			} catch (Exception e){
				return false;
			}
		}).collect(Collectors.toList());
	}

	@Override
	public List<Customer> customersWhoBoughtMoreThan(int number) {
		return customers.stream().filter(c -> c.getBoughtProducts().size() > number).collect(Collectors.toList());
	}

	@Override
	public List<Customer> customersWhoSpentMoreThan(double price) {
		return customers.stream().filter(c -> c.getBoughtProducts().stream().mapToDouble(Product::getPrice).sum() > price).collect(Collectors.toList());
	}

	@Override
	public List<Customer> customersWithNoOrders() {
		return customers.stream().filter(c -> c.getBoughtProducts().size() == 0).collect(Collectors.toList());
	}

	@Override
	public void addProductToAllCustomers(Product p) {
		customers.stream().forEach(c -> c.getBoughtProducts().add(p));
	}

	@Override
	public double avgOrders(boolean includeEmpty) {
		if(includeEmpty) {
			return customers.stream().mapToDouble(c -> c.getBoughtProducts().stream().mapToDouble(Product::getPrice).sum()).sum() / customers.size();
		} else {
			return customers.stream().filter(c -> c.getBoughtProducts().size() > 0).mapToDouble(c -> c.getBoughtProducts().stream().mapToDouble(Product::getPrice).sum()).sum() / customers.stream().filter(c -> c.getBoughtProducts().size() > 0).collect(Collectors.toList()).size();
		}
	}

	@Override
	public boolean wasProductBought(Product p) {
		return customers.stream().filter(c -> c.getBoughtProducts().stream().filter(e -> e.equals(p)).count() > 0).collect(Collectors.toList()).size() > 0;
	}

	@Override
	public List<Product> mostPopularProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countBuys(Product p) {
		return customers.stream().mapToInt(c -> c.getBoughtProducts().stream().filter(q -> q.equals(p)).collect(Collectors.toList()).size()).sum();
	}

	@Override
	public int countCustomersWhoBought(Product p) {
		return customers.stream().filter(c -> c.getBoughtProducts().contains(p)).collect(Collectors.toList()).size();
	}

}
