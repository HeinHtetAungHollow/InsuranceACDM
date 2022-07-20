package models;

import java.sql.Date;
import java.util.Objects;

public class Register {
	private int register_id;
	private PlanDetail planDetail;
	private Customer customer;
	private Employee employee;
	private Date payDate;
	private float payAmount;
	private boolean active;

	public Register() {

	}

	public Register(int register_id, PlanDetail planDetail, Customer customer, Employee employee, Date payDate,
			float payAmount, boolean active) {
		super();
		this.register_id = register_id;
		this.planDetail = planDetail;
		this.customer = customer;
		this.employee = employee;
		this.payDate = payDate;
		this.payAmount = payAmount;
		this.active = active;
	}

	public int getRegister_id() {
		return register_id;
	}

	public void setRegister_id(int register_id) {
		this.register_id = register_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public float getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(float payAmount) {
		this.payAmount = payAmount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public PlanDetail getPlanDetail() {
		return planDetail;
	}

	public void setPlanDetail(PlanDetail planDetail) {
		this.planDetail = planDetail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, customer, employee, payAmount, payDate, planDetail, register_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		return active == other.active && Objects.equals(customer, other.customer)
				&& Objects.equals(employee, other.employee)
				&& Float.floatToIntBits(payAmount) == Float.floatToIntBits(other.payAmount)
				&& Objects.equals(payDate, other.payDate) && Objects.equals(planDetail, other.planDetail)
				&& register_id == other.register_id;
	}

	
	
}
