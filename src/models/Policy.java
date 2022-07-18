package models;

public  class Policy 
{
	
	private int id;

	private String plan_name;
	
	private int duration;
	
	private Category category;
	public Policy()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlanName() {
		return plan_name;
	}

	public void setPlanName(String plan_name) {
		this.plan_name = plan_name;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Policy(int id, String plan_name, int duration, Category category) {
		super();
		this.id = id;
		this.plan_name = plan_name;
		this.duration = duration;
		this.category = category;
	}

	
}
