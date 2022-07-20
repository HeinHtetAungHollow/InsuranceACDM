package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import models.Policy;
import models.Category;
import models.PaymentPlan;
import models.PlanDetail;
import repositories.PolicyRepo;
import repositories.CategoryRepo;
import repositories.PaymentPlanRepo;
import config.DBConfig;
import shared.exception.AppException;
import shared.mapper.PolicyMapper;
import shared.mapper.CategoryMapper;
import shared.mapper.PaymentPlanMapper;

public class PaymentPlanService implements PaymentPlanRepo {

	private final DBConfig dbConfig = new DBConfig();
	private PaymentPlanMapper paymentPlanMapper;

	public PaymentPlanService() {
		this.paymentPlanMapper = new PaymentPlanMapper();

		this.paymentPlanMapper.setPolicyRepo(new PolicyService());

	}

	public void savePaymentPlan(PaymentPlan paymentPlan) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("INSERT INTO payment_plan (payplan,poli_id)  VALUES (?,?);");

			ps.setInt(1, paymentPlan.getPayplan());
			ps.setInt(2, paymentPlan.getPolicy().getId());
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePaymentPlan(String id, PaymentPlan paymentPlan) {
//		System.out.print(String.valueOf(policy.getCategory().getId()));
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE payment_plan SET payplan = ?, poli_id = ? WHERE id = ?");

			ps.setInt(1, paymentPlan.getPayplan());
			ps.setInt(2, paymentPlan.getPolicy().getId());
			ps.setString(3, id);
			ps.executeUpdate();

			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<PaymentPlan> findAllPaymentPlans() {

		List<PaymentPlan> paymentPlanList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM payment_plan " + "INNER JOIN policy_plan "
					+ "ON payment_plan.poli_id=policy_plan.policy_id " + "ORDER BY payment_plan.id";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				PaymentPlan p = new PaymentPlan();
				paymentPlanList.add(this.paymentPlanMapper.mapToPaymentPlan(p, rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paymentPlanList;
	}

	public PaymentPlan findById(String id) {
		PaymentPlan paymentPlan = new PaymentPlan();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM payment_plan" + " INNER JOIN policy_plan"
					+ " ON payment_plan.poli_id=policy_plan.policy_id " + " WHERE id = '" + id + "'";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				paymentPlan = this.paymentPlanMapper.mapToPaymentPlan(paymentPlan, rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paymentPlan;
	}

	@Override
	public List<PaymentPlan> findPaymentPlanListByPolicyId(String policyId) {

		List<PaymentPlan> paymentPlanList = new ArrayList<>();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM payment_plan INNER JOIN policy_plan ON payment_plan.poli_id = policy_plan.policy_id WHERE policy_id='"
					+ policyId + "'";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				PaymentPlan paymentPlan = new PaymentPlan();

				paymentPlanList.add(this.paymentPlanMapper.mapToPaymentPlan(paymentPlan, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return paymentPlanList;
	}

	public void deletePaymentPlan(String id) {

		String query = "DELETE FROM payment_plan WHERE id= ?;";

		PreparedStatement ps;
		try {
			ps = this.dbConfig.getConnection().prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void createPlanDetail(PlanDetail planDetail) {
		// TODO Auto-generated method stub

		try {
			PreparedStatement pst = this.dbConfig.getConnection().prepareStatement(
					"INSERT INTO plan_detail (payment_id,start_date,end_date,premium_amount) VALUES (?,?,?,?)");
			pst.setInt(1, planDetail.getPaymentPlan().getId());
			pst.setDate(2, planDetail.getStartDate());
			pst.setDate(3, planDetail.getEndDate());
			pst.setFloat(4, planDetail.getPremiumAmount());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "Already Exists!");
			} else {
				e.printStackTrace();
			}
		}
	}

	@Override
	public PaymentPlan findPaymentPlanByPlanDetailId(String planDetailId) {
		// TODO Auto-generated method stub
		PaymentPlan paymentPlan = new PaymentPlan();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "SELECT * FROM plan_detail INNER JOIN payment_plan ON"
					+ " plan_detail.payment_id=payment_plan.id WHERE plan_detail_id='" + planDetailId + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				paymentPlan = this.paymentPlanMapper.mapToPaymentPlan(paymentPlan, rs);
			}
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return paymentPlan;
	}

	@Override
	public List<PlanDetail> findPlanDetailByCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanDetail> findPlanDetailByPolicyId(String policyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
