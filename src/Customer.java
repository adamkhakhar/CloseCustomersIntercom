
public class Customer implements Comparable<Customer> {
	private int userId;
	private String name;

	
	public Customer(int userId, String name) {
		this.userId = userId;
		this.name = name;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	@Override
	public int compareTo(Customer compareCust) {
		int compareage = ((Customer) compareCust).getUserId();
		return this.userId - compareage;
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + " | UserId: " + this.userId;
	}
}

