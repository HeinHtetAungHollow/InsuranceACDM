package models;

import java.util.Objects;

public  class Category 
{
	
	private int id;

	private String category_name;
	
	
	public Category()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return category_name;
	}

	public void setCategoryName(String category_name) {
		this.category_name = category_name;
	}



	public Category(int id, String category_name) {
		super();
		this.id = id;
		this.category_name = category_name;
		
	}

	
	
	
	
}
