package shared.mapper;

import java.sql.ResultSet;

import models.Customer;
import models.Employee;
import models.PlanDetail;
import models.Register;
import repositories.PaymentPlanRepo;

public class RegisterMapper {
	private PaymentPlanRepo paymentPlanRepo;

	public void setPaymentPlanRepo(PaymentPlanRepo paymentPlanRepo) {
		this.paymentPlanRepo = paymentPlanRepo;
	}

	public Register mapToRegister(Register register, ResultSet rs) {

		try {
			while (rs.next()) {
				register.setRegister_id(rs.getInt("register_id"));

				PlanDetail planDetail = new PlanDetail();
				planDetail.setId(rs.getInt("plan_detail_id"));
				planDetail.setStartDate(rs.getDate("start_date"));
				planDetail.setEndDate(rs.getDate("end_date"));
				planDetail.setPremiumAmount(rs.getFloat("premium_amount"));
				planDetail.setPaymentPlan(this.paymentPlanRepo.findById(String.valueOf(rs.getInt("payment_id"))));
				register.setPlanDetail(planDetail);
				System.out.println(register.getPlanDetail().getPremiumAmount());

				Customer customer = new Customer();
				customer.setId(rs.getInt("cus_id"));
				customer.setCustomer_name(rs.getString("cus_name"));
				customer.setCustomer_nrc(rs.getString("cus_nrc"));
				customer.setCustomer_phone(rs.getString("cus_phone"));
				customer.setCustomer_address(rs.getString("cus_address"));
				customer.setCustomer_email(rs.getString("cus_email"));
				customer.setCustomer_occupation(rs.getString("cus_occupation"));
				customer.setCustomer_income((long) rs.getDouble("cus_income"));
				customer.setCustomer_age(rs.getInt("cus_age"));
				customer.setMedical_history(rs.getInt("cus_medicalHistory"));
				register.setCustomer(customer);
				System.out.println(register.getCustomer().getCustomer_name());
				
				Employee employee = new Employee();
				employee.setId(rs.getInt("emp_id"));
				employee.setName(rs.getString("emp_name"));
				employee.setNRC(rs.getString("emp_nrc"));
				employee.setPhone(rs.getString("emp_phone"));
				employee.setEmail(rs.getString("emp_email"));
				employee.setAddress(rs.getString("emp_address"));
				employee.setUsername(rs.getString("username"));
				register.setEmployee(employee);
				
				register.setPayDate(rs.getDate("pay_date"));
				register.setPayAmount(rs.getFloat("pay_amount"));
				register.setActive(rs.getBoolean("active"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return register;
	}
}
