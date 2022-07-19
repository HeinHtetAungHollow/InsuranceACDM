package repositories;

import models.Policy;

import java.util.List;

public interface PolicyRepo {
	void savePolicy(Policy policy);

	void updatePolicy(String id, Policy policy);

	List<Policy> findAllPolicys();

	Policy findById(String id);

	List<Policy> findPolicyListByCategoryId(String categoryId);

	void deletePolicy(String id);
}
