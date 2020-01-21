import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class CloseCustomersTest {


	//compareCustomers, sort
	@Test
	public void sortSingleCustomer() {
		Customer cust = new Customer(1, "Bob");
		ArrayList<Customer> arr = new ArrayList<Customer>();
		arr.add(cust);
		Collections.sort(arr);
		assertEquals(1, arr.get(0).getUserId());
	}
	
	@Test
	public void sortTwoCustomer() {
		Customer cust = new Customer(1, "Bob");
		Customer cust2 = new Customer(2, "Bob2");
		ArrayList<Customer> actual = new ArrayList<Customer>();
		actual.add(cust2);
		actual.add(cust);
		ArrayList<Customer> expected = new ArrayList<Customer>();
		expected.add(cust);
		expected.add(cust2);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void sortThreeCustomer() {
		Customer cust = new Customer(1, "Bob");
		Customer cust2 = new Customer(2, "Bob2");
		Customer cust3 = new Customer(3, "BOB3");
		ArrayList<Customer> actual = new ArrayList<Customer>();
		actual.add(cust2);
		actual.add(cust);
		actual.add(cust3);
		ArrayList<Customer> expected = new ArrayList<Customer>();
		expected.add(cust);
		expected.add(cust2);
		expected.add(cust3);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}
	
	//Test isWithin100
	@Test
	public void notWithin() {
		assertFalse(CloseCustomers.isWithin100(0, 0));
	}
	
	@Test
	public void notWithinClose() {
		assertFalse(CloseCustomers.isWithin100(35, 40));
	}
	
	@Test
	public void withinActual() {
		assertTrue(CloseCustomers.isWithin100(37.788802, -122.4025067));
	}
	
	@Test
	public void withinClose() {
		assertTrue(CloseCustomers.isWithin100(37, -122));
	}
	
	//makeValidTXTFile and parseFile test
	@Test
	public void withinSingle() {
		CloseCustomers single = new CloseCustomers("singleTest.txt");
		ArrayList<Customer> expected = new ArrayList<Customer>();
		Customer cust = new Customer(29, "Oliver Ahearn");
		expected.add(cust);
		assertEquals(29, single.getCloseCustomers().get(0).getUserId());
	}
	
	
	@Test
	public void notWithinSingle() {
		CloseCustomers single = new CloseCustomers("singleNotInTest.txt");
		assertEquals(0, single.getCloseCustomers().size());
	}
	
	@Test
	public void multiple() {
		CloseCustomers mult = new CloseCustomers("multipleTest.txt");
		ArrayList<Customer> actual = mult.getCloseCustomers();
		ArrayList<Customer> expected = new ArrayList<Customer>();
		Customer cust1 = new Customer(100, "Bob2");
		Customer cust2 = new Customer(101, "Bob3");
		Customer cust3 = new Customer(102, "Bob4");
		Customer cust4 = new Customer(103, "Bob6");
		Customer cust5 = new Customer(104, "Bob8");
		Customer cust6 = new Customer(105, "Bob10");
		Customer cust7 = new Customer(106, "Bob5");
		expected.add(cust1);
		expected.add(cust2);
		expected.add(cust3);
		expected.add(cust4);
		expected.add(cust5);
		expected.add(cust6);
		expected.add(cust7);
		assertTrue(actual.size() == 7);
		for (int i = 0; i < 7; i++) {
			assertTrue(actual.get(i).getUserId() == expected.get(i).getUserId());
		}
	}
	
	
	
	
}