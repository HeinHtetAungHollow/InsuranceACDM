package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import models.Register;
import repositories.RegisterRepo;
import shared.mapper.RegisterMapper;

public class RegisterServices implements RegisterRepo {
	private DBConfig dbConfig;
	private RegisterMapper registerMapper;

	public RegisterServices() {
		this.dbConfig = new DBConfig();
		this.registerMapper = new RegisterMapper();
		this.registerMapper.setPaymentPlanRepo(new PaymentPlanService());
	}

	@Override
	public void createRegister(Register register) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.dbConfig.getConnection().prepareStatement(
					"INSERT INTO register(planDetail_id,customer_id,employee_id,pay_date,pay_amount,active) VALUES"
							+ "(?,?,?,?,?,?)");
			pst.setInt(1, register.getPlanDetail().getId());
			pst.setInt(2, register.getCustomer().getId());
			pst.setInt(3, register.getEmployee().getId());
			pst.setDate(4, register.getPayDate());
			pst.setFloat(5, register.getPayAmount());
			pst.setBoolean(6, register.isActive());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "Already Exists!");
			} else {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Register> loadAllRegister() {
		// TODO Auto-generated method stub
		List<Register> registerList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "SELECT * FROM register INNER JOIN plan_detail ON register.planDetail_id=plan_detail.plan_detail_id "
					+ "INNER JOIN customer ON register.customer_id=customer.cus_id "
					+ "INNER JOIN employee ON register.employee_id=emp_id WHERE register.register_id=2";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Register register = new Register();
				registerList.add(this.registerMapper.mapToRegister(register, rs));
			}
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return registerList;
	}

	@Override
	public Register findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register findByCustomerId(String customer_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register findByPlanDetailId(String planDetail_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRegister(String id, Register register) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.dbConfig.getConnection().prepareStatement(
					"UPDATE register SET planDetail_id=?,customer_id=?,employee_id=?,pay_date=?,pay_amount=? WHERE register_id='"
							+ id + "'");
			pst.setInt(1, register.getPlanDetail().getId());
			pst.setInt(2, register.getCustomer().getId());
			pst.setInt(3, register.getEmployee().getId());
			pst.setDate(4, register.getPayDate());
			pst.setFloat(5, register.getPayAmount());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRegister(String id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.dbConfig.getConnection()
					.prepareStatement("DELETE FROM register WHERE register_id='" + id + "'");
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void inactiveRegister(String id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.dbConfig.getConnection()
					.prepareStatement("UPDATE register SET active=? WHERE register_id= '" + id + "'");
			pst.setBoolean(1, false);
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
