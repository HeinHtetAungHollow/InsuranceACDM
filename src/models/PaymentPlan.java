package models;

import java.util.ArrayList;
import java.util.List;

public  class PaymentPlan 
{
	
	private int id;

	private int payplan;
	
	private Policy policy;
	
	private List<PlanDetail> planDetails=new ArrayList<>();

	public PaymentPlan()
	{
		
	}

	public PaymentPlan(int id, int payplan, Policy policy, List<PlanDetail> planDetails) {
		super();
		this.id = id;
		this.payplan = payplan;
		this.policy = policy;
		this.planDetails = planDetails;
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

	public List<PlanDetail> getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(List<PlanDetail> planDetails) {
		this.planDetails = planDetails;
	}
	
	
	
}
