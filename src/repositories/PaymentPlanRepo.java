package repositories;

import models.PaymentPlan;
import models.PlanDetail;

import java.util.List;

public interface PaymentPlanRepo {
	void savePaymentPlan(PaymentPlan paymentPlan);

	void updatePaymentPlan(String id, PaymentPlan paymentPlan);

	void createPlanDetail(PlanDetail planDetail);

	List<PaymentPlan> findAllPaymentPlans();

	List<PaymentPlan> findPaymentPlanListByPolicyId(String policyId);

	PaymentPlan findPaymentPlanByPlanDetailId(String planDetailId);

	PaymentPlan findById(String id);

	List<PlanDetail> findPlanDetailByCategoryId(String categoryId);

	List<PlanDetail> findPlanDetailByPolicyId(String policyId);

	void deletePaymentPlan(String id);
}
