package models;

public class CustomerDetail {
	private Customer customer;
	private Benefitiary benefitiary;

	public CustomerDetail() {

	}

	public CustomerDetail(Customer customer, Benefitiary benefitiary) {
		super();
		this.customer = customer;
		this.benefitiary = benefitiary;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Benefitiary getBenefitiary() {
		return benefitiary;
	}

	public void setBenefitiary(Benefitiary benefitiary) {
		this.benefitiary = benefitiary;
	}

}
