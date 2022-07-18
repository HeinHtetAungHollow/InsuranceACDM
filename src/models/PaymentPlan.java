package models;

public  class PaymentPlan 
{
	
	private int id;

	private int payplan;
	
	private Policy policy;
	
	

	public PaymentPlan()
	{
		
	}

	public PaymentPlan(int id, int payplan, Policy policy) {
		super();
		this.id = id;
		this.payplan = payplan;
		this.policy = policy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPayplan() {
		return payplan;
	}

	public void setPayplan(int payplan) {
		this.payplan = payplan;
	}
	
	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
}
