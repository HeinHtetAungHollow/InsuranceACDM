package shared.mapper;

import models.Category;
import models.Policy;

import java.sql.ResultSet;

public class PolicyMapper {

    public Policy mapToPolicy(Policy policy, ResultSet rs) {
        try {
        	policy.setId(rs.getInt("policy_id"));
        	policy.setPlanName(rs.getString("plan_name"));
        	policy.setDuration(rs.getInt("duration"));
        	Category category=new Category();
        	category.setId(rs.getInt("cat_id"));
        	System.out.println(category.getId());
        	category.setCategoryName(rs.getString("category_name"));
        	policy.setCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return policy;
    }
}
