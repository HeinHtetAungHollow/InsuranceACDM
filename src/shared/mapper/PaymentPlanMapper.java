package shared.mapper;

import models.Category;
import models.PaymentPlan;
import models.PlanDetail;
import models.Policy;
import repositories.PaymentPlanRepo;
import repositories.PolicyRepo;

import java.sql.ResultSet;

public class PaymentPlanMapper {
	private PaymentPlanRepo paymentPlanRepo;
	private PolicyRepo policyRepo;

	public void setPaymentPlanRepo(PaymentPlanRepo paymentPlanRepo) {
		this.paymentPlanRepo = paymentPlanRepo;
	}

	public void setPolicyRepo(PolicyRepo policyRepo) {
		this.policyRepo = policyRepo;
	}

	public PaymentPlan mapToPaymentPlan(PaymentPlan paymentPlan, ResultSet rs) {
		try {
			paymentPlan.setId(rs.getInt("id"));
			paymentPlan.setPayplan(rs.getInt("payplan"));
			paymentPlan.setPolicy(this.policyRepo.findById(String.valueOf(rs.getInt("poli_id"))));
			System.out.println(paymentPlan.getPolicy().getCategory().getCategory_name());;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentPlan;
	}

	public PlanDetail mapToPlanDetail(PlanDetail planDetail, ResultSet rs) {
		try {
			planDetail.setId(rs.getInt("plan_detail_id"));
			planDetail.setPaymentPlan(this.paymentPlanRepo.findById(String.valueOf(rs.getInt("payment_id"))));
			planDetail.setStartDate(rs.getDate("start_date"));
			planDetail.setEndDate(rs.getDate("end_date"));
			planDetail.setPremiumAmount(rs.getFloat("premium_amount"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return planDetail;
	}
}
