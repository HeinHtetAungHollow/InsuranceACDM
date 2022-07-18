package shared.mapper;

import models.PaymentPlan;

import java.sql.ResultSet;

public class PaymentPlanMapper {

    public PaymentPlan mapToPaymentPlan(PaymentPlan paymentPlan, ResultSet rs) {
        try 
        {
        	paymentPlan.setId(rs.getInt("id"));
        	paymentPlan.setPayplan(rs.getInt("payplan_name"));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return paymentPlan;
    }
}
