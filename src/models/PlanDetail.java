package models;

import java.sql.Date;
import java.util.Objects;

public class PlanDetail {
	private int id;
	private PaymentPlan paymentPlan;
	private Date startDate;
	private Date endDate;
	private float premiumAmount;

	public PlanDetail() {

	}

	public PlanDetail(int id, PaymentPlan paymentPlan, Date startDate, Date endDate, float premiumAmount) {
		super();
		this.id = id;
		this.paymentPlan = paymentPlan;
		this.startDate = startDate;
		this.endDate = endDate;
		this.premiumAmount = premiumAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PaymentPlan getPaymentPlan() {
		return paymentPlan;
	}

	public void setPaymentPlan(PaymentPlan paymentPlan) {
		this.paymentPlan = paymentPlan;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(float premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(paymentPlan, endDate, id, premiumAmount, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanDetail other = (PlanDetail) obj;
		return Objects.equals(paymentPlan, other.paymentPlan) && Objects.equals(endDate, other.endDate) && id == other.id
				&& Float.floatToIntBits(premiumAmount) == Float.floatToIntBits(other.premiumAmount)
				&& Objects.equals(startDate, other.startDate);
	}

}
