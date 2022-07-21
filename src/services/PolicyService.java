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
import shared.mapper.PolicyMapper;




public class PolicyService implements PolicyRepo{

	private final DBConfig dbConfig = new DBConfig();
	private PolicyMapper policyMapper;

	public PolicyService() {
		this.policyMapper = new PolicyMapper();
		
	}

	public void savePolicy(Policy policy) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("INSERT INTO policy_plan (plan_name,duration,cat_id)  VALUES (?,?,?);");

			ps.setString(1, policy.getPlanName());
			ps.setInt(2, policy.getDuration());
			ps.setInt(3, policy.getCategory().getId());
			ps.executeUpdate();
			ps.close();

		} 
		catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void updatePolicy(String id, Policy policy) {
//		System.out.print(String.valueOf(policy.getCategory().getId()));
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE policy_plan SET plan_name = ?, duration = ?, cat_id = ? WHERE policy_id = ?");

			ps.setString(1, policy.getPlanName());
			ps.setInt(2, policy.getDuration());
			ps.setInt(3, policy.getCategory().getId());
			ps.setString(4, id);
			ps.executeUpdate();

			ps.close();

		} 
		catch (Exception e) {
            e.printStackTrace();
        }
	}

	public List<Policy> findAllPolicys() {

		List<Policy> policyList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM policy_plan " 
						+ "INNER JOIN insurance_category "
						+ "ON insurance_category.category_id=policy_plan.cat_id "
						+ "ORDER BY policy_plan.policy_id";

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
	
	@Override
    public List<Policy> findPolicyListByCategoryId(String categoryId) {

        List<Policy> policyList = new ArrayList<>();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM policy_plan\n" +
                    "INNER JOIN insurance_category\n" +
                    "ON insurance_category.category_id = policy_plan.cat_id\n" +
                    "WHERE category_id='" + categoryId + "'";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Policy policy = new Policy();

                policyList.add(this.policyMapper.mapToPolicy(policy, rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return policyList;
	}
	
	public void deletePolicy(String id) {

		String query = "DELETE FROM policy_plan WHERE policy_id= ?;";

		PreparedStatement ps;
		try {
			ps = this.dbConfig.getConnection().prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}
