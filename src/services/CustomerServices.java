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
import models.CustomerDetail;
import repositories.CustomerRepo;
import shared.mapper.CustomerMapper;

public class CustomerServices implements CustomerRepo {

	private DBConfig db_Config;
	private CustomerMapper customerMapper;
	private BenefitiaryServices benefitiaryServices;

	public CustomerServices() {
		this.db_Config = new DBConfig();
		this.customerMapper = new CustomerMapper();
		this.benefitiaryServices = new BenefitiaryServices();
		this.customerMapper.setCustomerRepo(this);
		this.customerMapper.setBenefitiaryRepo(benefitiaryServices);
	}

	@Override
	public void createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.db_Config.getConnection().prepareStatement(
					"INSERT INTO customer(cus_name,cus_nrc,cus_phone,cus_address,cus_email,cus_ocuupation,cus_income,cus_medicalHistory)"
							+ " values(?,?,?,?,?,?,?,?)");
			pst.setString(1, customer.getCustomer_name());
			pst.setString(2, customer.getCustomer_nrc());
			pst.setString(3, customer.getCustomer_phone());
			pst.setString(4, customer.getCustomer_address());
			pst.setString(5, customer.getCustomer_email());
			pst.setString(6, customer.getCustomer_occupation());
			pst.setInt(7, (int) customer.getCustomer_income());
			pst.setInt(8, customer.getMedical_history());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "Already Exists!");
			}
		}
	}

	@Override
	public List<Customer> loadAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> customerList = new ArrayList<>();
		try (Statement st = this.db_Config.getConnection().createStatement()) {
			String query = "SELECT * FROM customer";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Customer customer = new Customer();
				customerList.add(this.customerMapper.mapToCustomer(customer, rs));

			}
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerList;
	}

	@Override
	public Customer findById(String id) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		try (Statement st = this.db_Config.getConnection().createStatement()) {
			String query = "SELECT * FROM customer WHERE cus_id='" + id + "'";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				customer = this.customerMapper.mapToCustomer(customer, rs);
			}
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public void updateCustomer(String id, Customer customer) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.db_Config.getConnection().prepareStatement(
					"UPDATE customer SET cus_name=?,cus_nrc=?,cus_phone=?,cus_address=?,cus_email=?,cus_occupation=?,cus_medicalHistory=? WHERE cus_id=? ");
			pst.setString(1, customer.getCustomer_name());
			pst.setString(2, customer.getCustomer_nrc());
			pst.setString(3, customer.getCustomer_phone());
			pst.setString(4, customer.getCustomer_address());
			pst.setString(5, customer.getCustomer_email());
			pst.setString(6, customer.getCustomer_occupation());
			pst.setInt(7, (int) customer.getCustomer_income());
			pst.setInt(8, customer.getMedical_history());
			pst.setInt(8, Integer.parseInt(id));
			pst.executeUpdate();

			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "Already Exists!!");
			} else {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteCustomer(String id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.db_Config.getConnection()
					.prepareStatement("DELETE FROM customer WHERE cus_id='" + id + "'");
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void createCustomerDetail(List<CustomerDetail> customerDetailList) {
		// TODO Auto-generated method stub
		customerDetailList.forEach(cd -> {
			try {
				PreparedStatement pst = this.db_Config.getConnection()
						.prepareStatement("INSERT INTO customer_detail(customer_id,ben_id) vaules (?,?)");
				pst.setInt(1, getLatestCustomerId());
				pst.setInt(2, cd.getBenefitiary().getId());
				pst.executeUpdate();
				pst.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		});
	}

	public int getLatestCustomerId() {
		int id = 0;
		try (Statement st = this.db_Config.getConnection().createStatement()) {

			String query = "SELECT cus_id FROM customer ORDER BY cus_id DESC";

			ResultSet rs = st.executeQuery(query);
			rs.next();
			id = rs.getInt("cus_id");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public List<CustomerDetail> findCustomerDetailsByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		List<CustomerDetail> customerDetailList = new ArrayList<>();
		try (Statement st = this.db_Config.getConnection().createStatement()) {
			String query = "SELECT * FROM customer_detail WHERE customer_id='" + customerId + "'";
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()) {
				CustomerDetail customerDetail=new CustomerDetail();
				customerDetailList.add(this.customerMapper.mapToCustomerDetail(customerDetail, rs));
			}
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerDetailList;
	}

	@Override
	public List<CustomerDetail> findCustomerDetailsByBenefitiaryId(String benefitiaryId) {
		// TODO Auto-generated method stub
		List<CustomerDetail> customerDetailList = new ArrayList<>();
		try (Statement st = this.db_Config.getConnection().createStatement()) {
			String query = "SELECT * FROM customer_detail WHERE ben_id='" + benefitiaryId + "'";
			ResultSet rs=st.executeQuery(query);
			
			while(rs.next()) {
				CustomerDetail customerDetail=new CustomerDetail();
				customerDetailList.add(this.customerMapper.mapToCustomerDetail(customerDetail, rs));
			}
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerDetailList;
	}

}
