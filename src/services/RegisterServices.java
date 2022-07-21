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
import models.Customer;
import models.Employee;
import models.PlanDetail;
import models.Register;
import repositories.PaymentPlanRepo;
import repositories.RegisterRepo;
import shared.mapper.RegisterMapper;

public class RegisterServices implements RegisterRepo {
	private DBConfig dbConfig;
	private RegisterMapper registerMapper;
	private PaymentPlanRepo paymentPlanRepo;
	
	public RegisterServices() {
		this.dbConfig = new DBConfig();
		this.registerMapper = new RegisterMapper();
		this.registerMapper.setPaymentPlanRepo(new PaymentPlanService());
		this.paymentPlanRepo=new PaymentPlanService();
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
					+ "INNER JOIN employee ON register.employee_id=emp_id ORDER BY register.register_id";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Register register = new Register();
				register.setRegister_id(rs.getInt("register_id"));

				PlanDetail planDetail = new PlanDetail();
				planDetail.setId(rs.getInt("plan_detail_id"));
				planDetail.setStartDate(rs.getDate("start_date"));
				planDetail.setEndDate(rs.getDate("end_date"));
				planDetail.setPremiumAmount(rs.getFloat("premium_amount"));
				planDetail.setPaymentPlan(this.paymentPlanRepo.findById(String.valueOf(rs.getInt("payment_id"))));
				register.setPlanDetail(planDetail);
				
				Customer customer = new Customer();
				customer.setId(rs.getInt("cus_id"));
				customer.setCustomer_name(rs.getString("cus_name"));
				customer.setCustomer_nrc(rs.getString("cus_nrc"));
				customer.setCustomer_phone(rs.getString("cus_phone"));
				customer.setCustomer_address(rs.getString("cus_address"));
				customer.setCustomer_email(rs.getString("cus_email"));
				customer.setCustomer_occupation(rs.getString("cus_occupation"));
				customer.setCustomer_income((long) rs.getDouble("cus_income"));
				customer.setCustomer_age(rs.getInt("cus_age"));
				customer.setMedical_history(rs.getInt("cus_medicalHistory"));
				register.setCustomer(customer);
				
				Employee employee = new Employee();
				employee.setId(rs.getInt("emp_id"));
				employee.setName(rs.getString("emp_name"));
				employee.setNRC(rs.getString("emp_nrc"));
				employee.setPhone(rs.getString("emp_phone"));
				employee.setEmail(rs.getString("emp_email"));
				employee.setAddress(rs.getString("emp_address"));
				employee.setUsername(rs.getString("username"));
				register.setEmployee(employee);
				
				register.setPayDate(rs.getDate("pay_date"));
				register.setPayAmount(rs.getFloat("pay_amount"));
				register.setActive(rs.getBoolean("active"));
				registerList.add(register);
			}
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return registerList;
	}
	
	public List<Register> loadAllActiveRegisters(){
		List<Register> registerList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "SELECT * FROM register INNER JOIN plan_detail ON register.planDetail_id=plan_detail.plan_detail_id "
					+ "INNER JOIN customer ON register.customer_id=customer.cus_id "
					+ "INNER JOIN employee ON register.employee_id=emp_id WHERE register.active=1";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Register register = new Register();
				register.setRegister_id(rs.getInt("register_id"));

				PlanDetail planDetail = new PlanDetail();
				planDetail.setId(rs.getInt("plan_detail_id"));
				planDetail.setStartDate(rs.getDate("start_date"));
				planDetail.setEndDate(rs.getDate("end_date"));
				planDetail.setPremiumAmount(rs.getFloat("premium_amount"));
				planDetail.setPaymentPlan(this.paymentPlanRepo.findById(String.valueOf(rs.getInt("payment_id"))));
				register.setPlanDetail(planDetail);
				
				Customer customer = new Customer();
				customer.setId(rs.getInt("cus_id"));
				customer.setCustomer_name(rs.getString("cus_name"));
				customer.setCustomer_nrc(rs.getString("cus_nrc"));
				customer.setCustomer_phone(rs.getString("cus_phone"));
				customer.setCustomer_address(rs.getString("cus_address"));
				customer.setCustomer_email(rs.getString("cus_email"));
				customer.setCustomer_occupation(rs.getString("cus_occupation"));
				customer.setCustomer_income((long) rs.getDouble("cus_income"));
				customer.setCustomer_age(rs.getInt("cus_age"));
				customer.setMedical_history(rs.getInt("cus_medicalHistory"));
				register.setCustomer(customer);
				
				Employee employee = new Employee();
				employee.setId(rs.getInt("emp_id"));
				employee.setName(rs.getString("emp_name"));
				employee.setNRC(rs.getString("emp_nrc"));
				employee.setPhone(rs.getString("emp_phone"));
				employee.setEmail(rs.getString("emp_email"));
				employee.setAddress(rs.getString("emp_address"));
				employee.setUsername(rs.getString("username"));
				register.setEmployee(employee);
				
				register.setPayDate(rs.getDate("pay_date"));
				register.setPayAmount(rs.getFloat("pay_amount"));
				register.setActive(rs.getBoolean("active"));
				registerList.add(register);
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
