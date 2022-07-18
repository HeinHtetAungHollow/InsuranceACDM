package repositories;

import java.util.List;

import models.Benefitiary;

public interface BenefitiaryRepo {
	void createBenefitiary(Benefitiary benefitiary);
	
	List<Benefitiary> loadAllBenefitiary();
	
	Benefitiary findById(String id);
	
	void updateBenefitiary(String id,Benefitiary benefitiary);
	
	void deleteBenefitiary(String id);
	
}
