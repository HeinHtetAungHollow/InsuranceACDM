package models;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private int id;

	private String category_name;

	private List<PlanDetail> planDetails = new ArrayList<>();;

	public Category() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public List<PlanDetail> getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(List<PlanDetail> planDetails) {
		this.planDetails = planDetails;
	}

	public Category(int id, String category_name, List<PlanDetail> planDetails) {
		super();
		this.id = id;
		this.category_name = category_name;
		this.planDetails = planDetails;
	}

}
