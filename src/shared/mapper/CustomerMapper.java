package shared.mapper;

import java.sql.ResultSet;

import models.Customer;
import models.CustomerDetail;
import repositories.BenefitiaryRepo;
import repositories.CustomerRepo;

public class CustomerMapper {
	private CustomerRepo customerRepo;
	private BenefitiaryRepo benefitiaryRepo;

	public void setCustomerRepo(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	public void setBenefitiaryRepo(BenefitiaryRepo benefitiaryRepo) {
		this.benefitiaryRepo = benefitiaryRepo;
	}

	public Customer mapToCustomer(Customer customer, ResultSet rs) {

		try {
			while (rs.next()) {
				customer.setId(rs.getInt("cus_id"));
				customer.setCustomer_name(rs.getString("cus_name"));
				customer.setCustomer_nrc(rs.getString("cus_nrc"));
				customer.setCustomer_phone(rs.getString("cus_phone"));
				customer.setCustomer_address(rs.getString("cus_address"));
				customer.setCustomer_email(rs.getString("cus_email"));
				customer.setCustomer_occupation(rs.getString("cus_occupation"));
				customer.setCustomer_income((long) rs.getInt("cus_income"));
				customer.setMedical_history(rs.getInt("cus_medicalHistory"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return customer;
	}

	public CustomerDetail mapToCustomerDetail(CustomerDetail customerDetail, ResultSet rs) {

		try {
			while (rs.next()) {
				customerDetail.setCustomer(
						this.customerRepo.findById(String.valueOf(rs.getInt("customer_id"))));
				customerDetail.setBenefitiary(
						this.benefitiaryRepo.findById(String.valueOf(rs.getInt("ben_id"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return customerDetail;
	}
}
