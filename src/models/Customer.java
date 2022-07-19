package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hein htet aung
 *
 */
public class Customer {
	private int id;
	private String customer_name;
	private String customer_nrc;
	private String customer_phone;
	private String customer_address;
	private String customer_email;
	private String customer_occupation;
	private long customer_income;
	private int medical_history;
	private int customer_age;
	private List<CustomerDetail> CustomerDetail=new ArrayList<>();

	public Customer() {

	}

	public Customer(int id, String customer_name, String customer_nrc, String customer_phone, String customer_address,
			String customer_email, String customer_occupation, long customer_income, int medical_history,
			int customer_age) {
		super();
		this.id = id;
		this.customer_name = customer_name;
		this.customer_nrc = customer_nrc;
		this.customer_phone = customer_phone;
		this.customer_address = customer_address;
		this.customer_email = customer_email;
		this.customer_occupation = customer_occupation;
		this.customer_income = customer_income;
		this.medical_history = medical_history;
		this.customer_age = customer_age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_nrc() {
		return customer_nrc;
	}

	public void setCustomer_nrc(String customer_nrc) {
		this.customer_nrc = customer_nrc;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_occupation() {
		return customer_occupation;
	}

	public void setCustomer_occupation(String customer_occupation) {
		this.customer_occupation = customer_occupation;
	}

	public long getCustomer_income() {
		return customer_income;
	}

	public void setCustomer_income(long customer_income) {
		this.customer_income = customer_income;
	}

	public int getMedical_history() {
		return medical_history;
	}

	public void setMedical_history(int medical_history) {
		this.medical_history = medical_history;
	}

	public List<CustomerDetail> getCustomerDetail() {
		return CustomerDetail;
	}

	public void setCustomerDetail(List<CustomerDetail> customerDetail) {
		this.CustomerDetail = customerDetail;
	}

	public int getCustomer_age() {
		return customer_age;
	}

	public void setCustomer_age(int customer_age) {
		this.customer_age = customer_age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CustomerDetail, customer_address, customer_email, customer_income, customer_name,
				customer_nrc, customer_occupation, customer_phone, id, medical_history);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(CustomerDetail, other.CustomerDetail)
				&& Objects.equals(customer_address, other.customer_address)
				&& Objects.equals(customer_email, other.customer_email) && customer_income == other.customer_income
				&& Objects.equals(customer_name, other.customer_name)
				&& Objects.equals(customer_nrc, other.customer_nrc)
				&& Objects.equals(customer_occupation, other.customer_occupation)
				&& Objects.equals(customer_phone, other.customer_phone) && id == other.id
				&& medical_history == other.medical_history;
	}

}
