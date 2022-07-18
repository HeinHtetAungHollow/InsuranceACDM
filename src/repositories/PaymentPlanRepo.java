package repositories;

import models.PaymentPlan;

import java.util.List;

public interface PaymentPlanRepo {
    void saveCategory(PaymentPlan paymentPlan);

    void updatePaymentPlan(String id, PaymentPlan paymentPlan);

    List<PaymentPlan> findAllPaymentPlans();

    PaymentPlan findById(String id);

    void deletePaymentPlan(String id);
}
