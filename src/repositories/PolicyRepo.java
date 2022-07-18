package repositories;

import models.Policy;

import java.util.List;

public interface PolicyRepo {
    void savePolicy(Policy policy);

    void updatePolicy(String id, Policy policy);

    List<Policy> findAllPolicys();

    Policy findById(String id);

    void deletePolicy(String id);
}
