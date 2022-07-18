package shared.mapper;

import java.sql.ResultSet;

import models.Benefitiary;

public class BenefitiaryMapper {
	public Benefitiary mapToBenefitiary(Benefitiary benefitiary,ResultSet rs) {
		
		try {
			while(rs.next()) {
				benefitiary.setId(rs.getInt("rep_id"));
				benefitiary.setBenefitiary_name(rs.getString("rep_name"));
				benefitiary.setBenefitiary_nrc(rs.getString("rep_nrc"));
				benefitiary.setBenefitiary_phone(rs.getString("rep_phone"));
				benefitiary.setBenefitiary_email(rs.getString("rep_email"));
				benefitiary.setBenefitiary_address(rs.getString("rep_address"));
				benefitiary.setRelation(rs.getString("rep_relation"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return benefitiary;
	}
}
