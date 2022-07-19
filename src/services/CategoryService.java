package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.PlanDetail;
import repositories.CategoryRepo;
import config.DBConfig;
import shared.exception.AppException;
import shared.mapper.CategoryMapper;

import javax.swing.*;

import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

public class CategoryService implements CategoryRepo{

	private  DBConfig dbConfig;
	private CategoryMapper categoryMapper;

	public CategoryService() {
		this.categoryMapper = new CategoryMapper();
		this.dbConfig=new DBConfig();
	}

	public void saveCategory(Category category) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("INSERT INTO insurance_category (category_name)  VALUES (?);");

			ps.setString(1, category.getCategory_name());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			if (e instanceof SQLException) {
				JOptionPane.showMessageDialog(null, "Already Exists");
			} else {
				e.printStackTrace();
			}
		}
	}

	public void updateCategory(String id, Category category) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE insurance_category SET category_name = ? WHERE category_id = ?");

			ps.setString(1, category.getCategory_name());
			ps.setString(2, id);
			ps.executeUpdate();

			ps.close();

		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "Already Exists!");
			} else {
				e.printStackTrace();
			}
		}
	}

	public List<Category> findAllCategorys() {

		List<Category> categoryList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM insurance_category";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Category category = new Category();
				categoryList.add(this.categoryMapper.mapToCategory(category, rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categoryList;
	}

	public Category findById(String id) {
		Category category = new Category();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM insurance_category WHERE category_id = " + id + ";";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				category = this.categoryMapper.mapToCategory(category, rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return category;
	}

	public void deleteCategory(String id) {

		try {
			String query = "DELETE FROM insurance_category WHERE category_id= ?;";

			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(query);

			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


}
