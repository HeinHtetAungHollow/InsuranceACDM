package repositories;

import java.util.List;

import models.Register;

public interface RegisterRepo {
	void createRegister(Register register);
	
	List<Register> loadAllRegister();
	
	Register findById(String id);
	
	Register findByCustomerId(String customer_id);
	
	Register findByPlanDetailId(String planDetail_id);
	
	void updateRegister(String id,Register register);
	
	void deleteRegister(String id);
	
	void inactiveRegister(String id);
}
