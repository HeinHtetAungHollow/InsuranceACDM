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

	PaymentPlan findById(String id);

	void deletePaymentPlan(String id);
}
