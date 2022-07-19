package shared.mapper;

import models.Category;
import models.PlanDetail;
import repositories.CategoryRepo;

import java.sql.ResultSet;

public class CategoryMapper {
	
	public Category mapToCategory(Category category, ResultSet rs) {
		try {
			category.setId(rs.getInt("category_id"));
			System.out.println(category.getId());
			category.setCategory_name(rs.getString("category_name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	
}










