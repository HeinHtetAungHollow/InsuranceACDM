package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Policy;
import repositories.PolicyRepo;
import config.DBConfig;
import shared.exception.AppException;
import shared.mapper.PolicyMapper;

import javax.swing.*;

public class PolicyService {

	private final DBConfig dbConfig = new DBConfig();
	private PolicyMapper policyMapper;

	public PolicyService() {
		this.policyMapper = new PolicyMapper();
	}

	public void savePolicy(Policy policy) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("INSERT INTO policy_plan (plan_name,duration)  VALUES (?,?);");

			ps.setString(1, policy.getPlanName());
			ps.setInt(2, policy.getDuration());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			if (e instanceof SQLException) {
				JOptionPane.showMessageDialog(null, "Already Exists");
			}
		}
	}

	public void updatePolicy(String id, Policy policy) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE policy_plan SET plan_name = ?, duration = ? WHERE id = ?");

			ps.setString(1, policy.getPlanName());
			ps.setInt(2, policy.getDuration());
			ps.setString(3, id);
			ps.execute();

			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Policy> findAllPolicys() {

		List<Policy> policyList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM policy_plan " + "INNER JOIN insurance_category "
					+ "ON insurance_category.category_id=policy_plan.cat_id";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Policy p = new Policy();
				policyList.add(this.policyMapper.mapToPolicy(p, rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return policyList;
	}

	public Policy findById(String id) {
		Policy policy = new Policy();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM policy_plan INNER JOIN insurance_category ON insurance_category.category_id=policy_plan.cat_id WHERE policy_id = '" + id + "'";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				policy = this.policyMapper.mapToPolicy(policy, rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return policy;
	}

	public void deletePolicy(String id) throws SQLException {

		String query = "DELETE FROM policy_plan WHERE id= ?;";

		PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(query);

		ps.setString(1, id);
		ps.executeUpdate();
		ps.close();

	}

}
